package com.ramoncinp.mydollars.domain.formatters

import java.text.SimpleDateFormat
import java.util.*

fun simpleDateFormatter() = SimpleDateFormat("HH:mm:ss\ndd/MMM/yyyy", Locale.getDefault())

fun Date.toFormattedDate() = simpleDateFormatter().format(this).uppercase()
