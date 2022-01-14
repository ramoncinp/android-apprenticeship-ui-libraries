package com.ramoncinp.mydollars.ui.transaction

import com.ramoncinp.mydollars.data.models.TransactionType

interface AddTransactionContract {
    interface Presenter {
        fun addTransaction(description: String, amount: Double, type: TransactionType)
        fun validateDescription(description: String): Boolean
        fun validateAmount(amount: String): Boolean
    }

    interface View {
        fun transactionCreated()
    }
}
