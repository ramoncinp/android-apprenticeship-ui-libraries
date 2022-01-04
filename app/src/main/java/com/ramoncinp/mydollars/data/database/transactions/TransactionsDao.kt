package com.ramoncinp.mydollars.data.database.transactions

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TransactionsDao {

    @Insert
    suspend fun insert(transaction: Transaction)

    @Query("SELECT * from TRANSACTIONS_TABLE")
    suspend fun getTransactions(): List<Transaction>
}
