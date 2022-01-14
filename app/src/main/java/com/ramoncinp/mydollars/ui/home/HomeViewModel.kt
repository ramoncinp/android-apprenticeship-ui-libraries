package com.ramoncinp.mydollars.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ramoncinp.mydollars.data.TransactionsManager
import com.ramoncinp.mydollars.data.models.Transaction
import com.ramoncinp.mydollars.domain.formatters.toFormattedAmount

class HomeViewModel(private val transactionsManager: TransactionsManager) : ViewModel() {

    private val _balanceData = MutableLiveData<String>()
    val balanceData: LiveData<String>
        get() = _balanceData

    private val _transactionsData = MutableLiveData<List<Transaction>>()
    val transactionsData: LiveData<List<Transaction>>
        get() = _transactionsData

    fun getBalanceData() {
        val balance = transactionsManager.balance
        val formattedBalance = "\$${balance.toFormattedAmount()}"
        _balanceData.value = formattedBalance
    }

    private fun orderTransactions(transactions: List<Transaction>) =
        transactions.sortedByDescending { it.date }

    fun getTransactions() {
        val transactions = transactionsManager.transactions
        val orderedTransactions = orderTransactions(transactions)
        _transactionsData.value = orderedTransactions
    }

    class Factory(private val transactionsManager: TransactionsManager) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return HomeViewModel(transactionsManager) as T
        }
    }
}
