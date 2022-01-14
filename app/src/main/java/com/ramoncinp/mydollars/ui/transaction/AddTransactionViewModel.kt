package com.ramoncinp.mydollars.ui.transaction

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ramoncinp.mydollars.data.models.Transaction
import com.ramoncinp.mydollars.data.models.TransactionType
import com.ramoncinp.mydollars.domain.usecases.AddTransactionUseCase

class AddTransactionViewModel(
    private val addTransactionUseCase: AddTransactionUseCase
) : ViewModel() {

    private val _transactionCreated = MutableLiveData<Boolean>()
    val transactionCreated: LiveData<Boolean>
        get() = _transactionCreated

    fun addTransaction(description: String, amount: Double, type: TransactionType) {
        val transaction = Transaction(
            description = description,
            amount = amount,
            type = type
        )

        addTransactionUseCase.invoke(transaction)

        _transactionCreated.value = true
    }

    fun validateDescription(description: String) = description.isNotEmpty()

    fun validateAmount(amount: String) = try {
        val amountValue = amount.toDouble()
        amountValue > 0
    } catch (e: Exception) {
        false
    }

    class Factory(private val addTransactionUseCase: AddTransactionUseCase) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return AddTransactionViewModel(addTransactionUseCase) as T
        }
    }
}
