package com.ramoncinp.mydollars.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ramoncinp.mydollars.data.database.accounts.Account
import com.ramoncinp.mydollars.data.database.accounts.AccountsDao
import com.ramoncinp.mydollars.data.database.transactions.Transaction
import com.ramoncinp.mydollars.data.database.transactions.TransactionsDao

@Database(entities = [Account::class, Transaction::class], version = 1, exportSchema = false)
abstract class DollarsDatabase : RoomDatabase() {

    abstract val accountsDao: AccountsDao
    abstract val transactionsDao: TransactionsDao
}
