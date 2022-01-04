package com.ramoncinp.mydollars.data.database.accounts

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

const val ACCOUNTS_TABLE_NAME = "accounts_table"

@Entity(tableName = ACCOUNTS_TABLE_NAME)
data class Account(
    @PrimaryKey
    val id: Long = 0L,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "amount")
    val amount: Double
)
