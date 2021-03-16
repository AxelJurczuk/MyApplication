package com.example.android.data.di.providers

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.createDataStore
import com.example.android.data.BuildConfig
import com.example.android.data.local.BankDatabase
import com.example.android.data.remote.ITransactionAPI
import com.example.android.data.remote.interceptors.MockInterceptor
import com.example.android.data.repositories.TransactionRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

fun provideOkHttpClient(mockInterceptor: Interceptor?): OkHttpClient{
    val client = OkHttpClient().newBuilder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
    if (BuildConfig.FLAVOR == "mock"){
        mockInterceptor?.let {
            client.addInterceptor(mockInterceptor)
        }
    }
    return client.build()
}

fun provideMockInterceptor(application: Application): Interceptor{
    return MockInterceptor(application)
}

fun provideGson(): Gson {
    return GsonBuilder()
            .setLenient()
            .serializeNulls()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            .create()
}

fun provideRetrofit(httpClient: OkHttpClient, gson: Gson): Retrofit = Retrofit.Builder()
        .client(httpClient)
        .baseUrl(BuildConfig.BaseURL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

fun provideTransactionApi (retrofit: Retrofit): ITransactionAPI
= retrofit
        .create(ITransactionAPI::class.java)

fun provideBankDatabase(application: Application): BankDatabase {
    return BankDatabase.getInstance(application)
}

fun provideProfileDataStore(context: Context):DataStore<Preferences>{
    return context.createDataStore(name = "firstName")
}

fun provideTransactionRepository(retrofit: ITransactionAPI, bankDB: BankDatabase, dataStore: DataStore<Preferences>): TransactionRepository
= TransactionRepository(retrofit, bankDB, dataStore)


