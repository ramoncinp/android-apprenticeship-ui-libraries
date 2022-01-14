package com.ramoncinp.mydollars.domain.usecases

import com.ramoncinp.mydollars.data.TransactionsManager
import com.ramoncinp.mydollars.data.models.Transaction

class GetTransactionsUseCase(
    private val transactionsManager: TransactionsManager
) {

    operator fun invoke(): List<Transaction> {
        val transactions = transactionsManager.transactions
        return orderTransactions(transactions)
    }

    private fun orderTransactions(transactions: List<Transaction>) =
        transactions.sortedByDescending { it.date }
}
