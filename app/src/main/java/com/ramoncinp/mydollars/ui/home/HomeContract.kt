package com.ramoncinp.mydollars.ui.home

import com.ramoncinp.mydollars.data.models.Transaction

interface HomeContract {
    interface Presenter {
        fun getBalanceData()
        fun getTransactions()
    }

    interface View {
        fun setBalanceData(balance: String)
        fun setTransactionsData(transactions: List<Transaction>)
        fun noTransactionsData()
    }
}
