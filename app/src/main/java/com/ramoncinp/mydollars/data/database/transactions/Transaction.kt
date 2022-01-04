package com.ramoncinp.mydollars.data.database.transactions

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

const val TRANSACTION_TABLE_NAME = "transactions_table"

enum class TransactionType {
    EXPENSE,
    INCOME
}

@Entity(tableName = TRANSACTION_TABLE_NAME)
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,

    @ColumnInfo(name = "date_millis")
    val date: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "amount")
    val amount: Double,

    @ColumnInfo(name = "type")
    val type: String
)
