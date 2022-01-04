package com.ramoncinp.mydollars.domain.formatters

import java.text.DecimalFormat

private val decimalFormat = DecimalFormat("###,###,##0.00")

fun Double.toFormattedAmount() = decimalFormat.format(this)
