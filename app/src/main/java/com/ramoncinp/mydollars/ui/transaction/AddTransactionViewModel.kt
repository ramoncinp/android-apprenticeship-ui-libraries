package com.ramoncinp.mydollars.ui.transaction

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ramoncinp.mydollars.data.TransactionsManager
import com.ramoncinp.mydollars.data.models.Transaction
import com.ramoncinp.mydollars.data.models.TransactionType

class AddTransactionViewModel(
    private val transactionsManager: TransactionsManager
) : ViewModel() {

    private val _transactionCreated = MutableLiveData<Boolean>()
    val transactionCreated: LiveData<Boolean>
        get() = _transactionCreated

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

        _transactionCreated.value = true
    }

    fun validateDescription(description: String) = description.isNotEmpty()

    fun validateAmount(amount: String) = try {
        val amountValue = amount.toDouble()
        amountValue > 0
    } catch (e: Exception) {
        false
    }

    class Factory(private val transactionsManager: TransactionsManager) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return AddTransactionViewModel(transactionsManager) as T
        }
    }
}
