package com.ramoncinp.mydollars.ui.transaction

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramoncinp.mydollars.data.database.accounts.AccountsDao
import com.ramoncinp.mydollars.data.database.transactions.Transaction
import com.ramoncinp.mydollars.data.database.transactions.TransactionType
import com.ramoncinp.mydollars.data.database.transactions.TransactionsDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTransactionViewModel @Inject constructor(
    private val accountsDao: AccountsDao,
    private val transactionsDao: TransactionsDao
) : ViewModel() {

    private val _isTransactionDone = MutableLiveData<Boolean>()
    val isTransactionDone: LiveData<Boolean>
        get() = _isTransactionDone

    fun createTransaction(transaction: Transaction) {
        viewModelScope.launch {
            transactionsDao.insert(transaction)
            updateAccount(transaction.amount, transaction.type)
            _isTransactionDone.value = true
        }
    }

    private suspend fun updateAccount(amount: Double, type: String) {
        val account = accountsDao.getAccount()!!
        var currentBalance = account.amount
        currentBalance = when (type) {
            TransactionType.INCOME.name -> currentBalance.plus(amount)
            TransactionType.EXPENSE.name -> currentBalance.minus(amount)
            else -> currentBalance
        }
        accountsDao.update(account.copy(amount = currentBalance))
    }
}
