package com.ramoncinp.mydollars.utils

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager

fun Activity.hideKeyboard() {
    val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputManager.hideSoftInputFromWindow(
        currentFocus!!.windowToken,
        InputMethodManager.HIDE_NOT_ALWAYS
    )
}
