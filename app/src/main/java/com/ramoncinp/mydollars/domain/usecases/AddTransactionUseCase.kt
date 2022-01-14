package com.ramoncinp.mydollars.domain.usecases

import com.ramoncinp.mydollars.data.TransactionsManager
import com.ramoncinp.mydollars.data.models.Transaction
import com.ramoncinp.mydollars.data.models.TransactionType

class AddTransactionUseCase(
    private val transactionsManager: TransactionsManager
) {

    operator fun invoke(transaction: Transaction) {
        transactionsManager.transactions.add(transaction)
        transactionsManager.balance = when (transaction.type) {
            TransactionType.INCOME -> TransactionsManager.balance.plus(transaction.amount)
            TransactionType.EXPENSE -> TransactionsManager.balance.minus(transaction.amount)
        }
    }
}
