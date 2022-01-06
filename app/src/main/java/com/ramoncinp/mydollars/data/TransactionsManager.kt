package com.ramoncinp.mydollars.data

import com.ramoncinp.mydollars.data.models.Transaction

object TransactionsManager {
    val transactions = mutableListOf<Transaction>()
    var balance = 624.0
}
