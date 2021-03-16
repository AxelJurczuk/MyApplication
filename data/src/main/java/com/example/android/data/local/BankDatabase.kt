package com.example.android.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.android.data.commons.Constants.DATABASE_NAME
import com.example.android.data.models.TransactionDTO

@Database(entities = [TransactionDTO::class], version = 1)
abstract class BankDatabase: RoomDatabase() {
    abstract fun transactionDao(): TransactionDao
    companion object{
        @Volatile
        private var INSTANCE: BankDatabase? = null
        fun getInstance(context: Context): BankDatabase =
            INSTANCE ?: synchronized(this){
                INSTANCE ?: buildDatabase(context.applicationContext).also {
                    INSTANCE = it
                }
            }
        private fun buildDatabase(appContext: Context): BankDatabase{
            return Room.databaseBuilder(appContext, BankDatabase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}