package com.example.weather.utils

import android.content.Context
import android.os.SystemClock
import android.view.View
import android.view.inputmethod.InputMethodManager

object Util {
    const val API_KEY = "e15f28bd3e4e774d0238b1243f2cd4a1"
    const val MEASURE_SYSTEM = "metric"

    fun View.clickWithDebounce(debounceTime: Long = 600L, action: () -> Unit) {
        this.setOnClickListener(object : View.OnClickListener {
            private var lastClickTime: Long = 0

            override fun onClick(v: View) {
                if (SystemClock.elapsedRealtime() - lastClickTime < debounceTime) return
                else action()

                lastClickTime = SystemClock.elapsedRealtime()
            }
        })
    }

    fun View.showKeyboard() {
        this.requestFocus()
        val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
    }

    fun View.hideKeyboard() {
        val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
    }
   /* fun Locale.measureSystem(): String {
        return when (country.toUpperCase(this)) {
            //only countries that have not adopted the metric system are Myanmar (also known as Burma), Liberia and the United States.
            "US", "LR", "MM" -> ""
            else -> "metric"
        }
    }*/
}