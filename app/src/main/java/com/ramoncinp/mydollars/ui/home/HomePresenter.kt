package com.ramoncinp.mydollars.ui.home

import com.ramoncinp.mydollars.data.TransactionsManager
import com.ramoncinp.mydollars.data.models.Transaction
import com.ramoncinp.mydollars.domain.formatters.toFormattedAmount

class HomePresenter(
    private val transactionsManager: TransactionsManager,
    private val homeInteractor: HomeInteractor
) {

    fun getBalanceData() {
        val balance = transactionsManager.balance
        val formattedBalance = "\$${balance.toFormattedAmount()}"
        homeInteractor.setBalanceData(formattedBalance)
    }

    private fun orderTransactions(transactions: List<Transaction>) =
        transactions.sortedByDescending { it.date }

    fun getTransactions() {
        val transactions = transactionsManager.transactions
        val orderedTransactions = orderTransactions(transactions)

        if (orderedTransactions.isEmpty()) {
            homeInteractor.noTransactionsData()
            return
        }

        homeInteractor.setTransactionsData(orderedTransactions)
    }
}
