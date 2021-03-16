package com.example.android.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.android.data.commons.Constants.TABLE_TRANSACTIONS
import com.example.android.data.models.TransactionDTO

@Dao
interface TransactionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(transactions: List<TransactionDTO>)
    @Query("SELECT * FROM `$TABLE_TRANSACTIONS`")
    fun load(): LiveData<List<TransactionDTO>>
    @Query("DELETE FROM `$TABLE_TRANSACTIONS`")
    fun deleteAll()
}