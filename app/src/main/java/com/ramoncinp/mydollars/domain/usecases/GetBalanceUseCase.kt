package com.ramoncinp.mydollars.domain.usecases

import com.ramoncinp.mydollars.data.TransactionsManager

class GetBalanceUseCase(
    private val transactionsManager: TransactionsManager
) {

    operator fun invoke(): Double = transactionsManager.balance
}
