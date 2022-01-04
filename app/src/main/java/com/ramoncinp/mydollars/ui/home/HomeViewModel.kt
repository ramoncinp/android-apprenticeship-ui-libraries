package com.ramoncinp.mydollars.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramoncinp.mydollars.data.database.accounts.Account
import com.ramoncinp.mydollars.data.database.accounts.AccountsDao
import com.ramoncinp.mydollars.data.database.transactions.Transaction
import com.ramoncinp.mydollars.data.database.transactions.TransactionsDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val transactionsDao: TransactionsDao,
    private val accountsDao: AccountsDao
) : ViewModel() {

    private val _accountBalance = MutableLiveData<Double>()
    val accountBalance: LiveData<Double>
        get() = _accountBalance

    private val _transactions = MutableLiveData<List<Transaction>>()
    val transactions: LiveData<List<Transaction>>
        get() = _transactions

    fun getBalance() {
        viewModelScope.launch(Dispatchers.IO) {
            val account = accountsDao.getAccount()
            val amount = account?.amount ?: 0.0
            if (account == null) saveNewAccount()
            _accountBalance.postValue(amount)
        }
    }

    private fun createNewAccount() = Account(
        name = "Dollars",
        amount = 0.0
    )

    private suspend fun saveNewAccount() {
        val newAccount = createNewAccount()
        accountsDao.insert(newAccount)
    }

    fun getTransactions() {
        viewModelScope.launch(Dispatchers.IO) {
            val transactions = transactionsDao.getTransactions().sortedByDescending { it.date }
            _transactions.postValue(transactions)
        }
    }
}
