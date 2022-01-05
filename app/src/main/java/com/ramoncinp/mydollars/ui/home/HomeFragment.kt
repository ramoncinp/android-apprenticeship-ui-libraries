package com.ramoncinp.mydollars.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.ramoncinp.mydollars.R
import com.ramoncinp.mydollars.data.TransactionsManager
import com.ramoncinp.mydollars.data.models.Transaction
import com.ramoncinp.mydollars.domain.formatters.toFormattedAmount

class HomeFragment : Fragment(R.layout.home_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // TODO: Init views
    }

    private fun initViews() {
        // TODO: Take views references and set them
        setBalanceData(TransactionsManager.balance)
        setTransactionsData(TransactionsManager.transactions)
    }

    private fun setBalanceData(balance: Double) {
        val formattedBalance = "\$${balance.toFormattedAmount()}"
        // TODO: Show balance data in UI
    }

    private fun setTransactionsData(transactions: List<Transaction>) {

    }

    private fun showNoTransactions() {
        // TODO: Show in UI that there are no transactions
    }
}
