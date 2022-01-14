package com.ramoncinp.mydollars.ui.home

import com.ramoncinp.mydollars.data.models.Transaction

interface HomeInteractor {
    fun setBalanceData(balance: String)
    fun setTransactionsData(transactions: List<Transaction>)
    fun noTransactionsData()
}
