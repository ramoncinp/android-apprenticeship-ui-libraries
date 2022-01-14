package com.ramoncinp.mydollars.data.models

enum class TransactionType {
    EXPENSE,
    INCOME
}

data class Transaction(
    var id: Long = 0L,
    val date: Long = System.currentTimeMillis(),
    val description: String,
    val amount: Double,
    val type: TransactionType
)
