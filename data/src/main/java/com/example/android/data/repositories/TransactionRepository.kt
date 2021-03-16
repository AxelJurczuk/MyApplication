package com.example.android.data.repositories

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesKey
import androidx.lifecycle.LiveData
import com.example.android.data.commons.BaseRepository
import com.example.android.data.commons.Constants
import com.example.android.data.local.BankDatabase
import com.example.android.data.models.TransactionDTO
import com.example.android.data.remote.ITransactionAPI
import com.example.android.data.remote.ResultHandler
import com.example.android.data.utils.TransactionsUtil
import kotlinx.coroutines.flow.first
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class TransactionRepository(private val api: ITransactionAPI,
                            private val bankDB: BankDatabase,
                            private val dataStore:DataStore<Preferences>
                            ): BaseRepository() {

    val mTransactions: LiveData<List<TransactionDTO>> by lazy {
        bankDB.transactionDao().load()
    }

    //API
    suspend fun getTransactionsAndSave(): ResultHandler<String> {
        //Call to API and save in Room
        return when (val result = safeApiCall(call = { api.getTransactions() })) {
            is ResultHandler.Success -> {
                //Sort the list
                result.data.let {
                    var sortedList = result.data.toMutableList()
                        .sortedWith(compareByDescending { it.date })
                    sortedList = TransactionsUtil.filterTransactions(sortedList)
                    //Save data in Room
                    bankDB.transactionDao().save(sortedList)
                }
                //It is not necessary to return nothing, magic is done with liveData in Room
                ResultHandler.Success("Successful update")
            }
            is ResultHandler.GenericError -> result
            is ResultHandler.HttpError -> result
            is ResultHandler.NetworkError -> result
        }
    }
    //Database
    suspend fun deleteTransactions(){
        bankDB.transactionDao().deleteAll()
    }
    //DataStore Preferences
    //save
    suspend fun save(value: String) {
        val dataStoreKey = preferencesKey<String>(Constants.PREFERENCES_NAME_KEY)
        dataStore.edit { settings ->
            settings[dataStoreKey] = value
        }
    }
    //read
    suspend fun readName(): String? {
        val dataStoreKey = preferencesKey<String>(Constants.PREFERENCES_NAME_KEY)
        val preferences = dataStore.data.first()
        return preferences[dataStoreKey]
    }

}