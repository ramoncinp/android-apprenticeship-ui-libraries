package com.ramoncinp.mydollars.ui.utils

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.ramoncinp.mydollars.domain.formatters.toFormattedAmount

@BindingAdapter("amountFormatted")
fun TextView.setAmountFormatted(balance: Double) {
    val balanceText = "\$${balance.toFormattedAmount()}"
    text = balanceText
}
