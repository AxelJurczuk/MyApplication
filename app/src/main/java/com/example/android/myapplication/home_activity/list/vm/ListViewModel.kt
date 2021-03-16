package com.example.android.myapplication.home_activity.list.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.android.data.models.TransactionDTO
import com.example.android.data.remote.ResultHandler
import com.example.android.data.repositories.TransactionRepository
import com.example.android.myapplication.commons.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ListViewModel(private val repository: TransactionRepository): BaseViewModel() {

    val transactionsList: LiveData<List<TransactionDTO>> = repository.mTransactions

    fun fetchTransactions(){
        _isLoading.value = true
        viewModelScope.launch (Dispatchers.IO) {
            when (val result = repository.getTransactionsAndSave()){
                is ResultHandler.Success -> {
                    showMessage(result.data)
                }
                else -> {
                    setShowError(result)
                }
            }
            delay(5000)
            _isLoading.postValue(false)
        }
    }


    fun clearList() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteTransactions()
        }
    }


}
