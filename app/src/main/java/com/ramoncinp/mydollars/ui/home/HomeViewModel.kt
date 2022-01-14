package com.ramoncinp.mydollars.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ramoncinp.mydollars.data.models.Transaction
import com.ramoncinp.mydollars.domain.formatters.formatAsCurrency
import com.ramoncinp.mydollars.domain.usecases.GetBalanceUseCase
import com.ramoncinp.mydollars.domain.usecases.GetTransactionsUseCase

class HomeViewModel(
    private val getBalanceUseCase: GetBalanceUseCase,
    private val getTransactionsUseCase: GetTransactionsUseCase
) : ViewModel() {

    private val _balanceData = MutableLiveData<String>()
    val balanceData: LiveData<String>
        get() = _balanceData

    private val _transactionsData = MutableLiveData<List<Transaction>>()
    val transactionsData: LiveData<List<Transaction>>
        get() = _transactionsData

    fun getBalanceData() {
        val balance = getBalanceUseCase()
        val formattedBalance = balance.formatAsCurrency()
        _balanceData.value = formattedBalance
    }

    fun getTransactions() {
        _transactionsData.value = getTransactionsUseCase()
    }

    class Factory(
        private val getBalanceUseCase: GetBalanceUseCase,
        private val getTransactionsUseCase: GetTransactionsUseCase
    ) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return HomeViewModel(getBalanceUseCase, getTransactionsUseCase) as T
        }
    }
}
