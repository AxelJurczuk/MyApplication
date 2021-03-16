package com.example.android.data.remote

import com.example.android.data.models.TransactionDTO
import retrofit2.Response
import retrofit2.http.GET

interface ITransactionAPI {
    @GET("/transactions.json")
    suspend fun getTransactions(): Response<List<TransactionDTO>>
}