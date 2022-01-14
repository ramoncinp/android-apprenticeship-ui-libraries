package com.ramoncinp.mydollars.ui.transaction

import com.ramoncinp.mydollars.data.TransactionsManager
import com.ramoncinp.mydollars.data.models.Transaction
import com.ramoncinp.mydollars.data.models.TransactionType

class AddTransactionPresenter(
    private val transactionsManager: TransactionsManager,
    private val addTransactionInteractor: AddTransactionInteractor
) {

    fun addTransaction(description: String, amount: Double, type: TransactionType) {
        val transaction = Transaction(
            description = description,
            amount = amount,
            type = type.name
        )

        transactionsManager.transactions.add(transaction)

        transactionsManager.balance = when (type) {
            TransactionType.INCOME -> TransactionsManager.balance.plus(amount)
            TransactionType.EXPENSE -> TransactionsManager.balance.minus(amount)
        }

        addTransactionInteractor.transactionCreated()
    }

    fun validateDescription(description: String) = description.isNotEmpty()

    fun validateAmount(amount: String) = try {
        val amountValue = amount.toDouble()
        amountValue > 0
    } catch (e: Exception) {
        false
    }
}
