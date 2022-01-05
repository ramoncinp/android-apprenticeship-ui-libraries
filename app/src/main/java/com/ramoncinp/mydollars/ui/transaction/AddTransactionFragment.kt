package com.ramoncinp.mydollars.ui.transaction

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.ramoncinp.mydollars.R
import com.ramoncinp.mydollars.data.TransactionsManager
import com.ramoncinp.mydollars.data.models.Transaction
import com.ramoncinp.mydollars.data.models.TransactionType

class AddTransactionFragment : Fragment(R.layout.add_transaction_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // TODO: Initialize views
    }

    private fun initViews() {
        // TODO: Take views references and set them
    }

    private fun validateData(): Boolean {
        return false
    }

    private fun createTransaction(transactionType: TransactionType) {
        val description = "description"
        val amount = 0.0
        val transaction = Transaction(
            description = description,
            amount = amount,
            type = transactionType.name
        )

        TransactionsManager.transactions.add(transaction)

        TransactionsManager.balance = when (transactionType) {
            TransactionType.INCOME -> TransactionsManager.balance.plus(amount)
            TransactionType.EXPENSE -> TransactionsManager.balance.minus(amount)
        }
    }
}
