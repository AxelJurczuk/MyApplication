package com.example.android.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.android.data.commons.Constants

@Entity(tableName = Constants.TABLE_TRANSACTIONS)
data class TransactionDTO(
    @PrimaryKey val id: String,
    var date: String,
    val amount: String,
    val description: String?,
    val fee: String?,
    var total: String?
)