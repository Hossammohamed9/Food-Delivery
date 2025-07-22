package com.example.fooddelivery.core.presentation.util

import android.content.Context
import com.example.fooddelivery.R
import com.example.fooddelivery.core.domain.util.DataError

fun DataError.toString(context: Context): String {
    val resId = when(this) {
        DataError.REQUEST_TIMEOUT -> R.string.error_request_timeout
        DataError.NO_INTERNET -> R.string.error_no_internet
        DataError.SERVER_ERROR -> R.string.error_unknown
        DataError.PARSING_ERROR -> R.string.parsing_error
        DataError.UNKNOWN -> R.string.error_unknown
        DataError.DATABASE_ERROR -> R.string.database_error
    }
    return context.getString(resId)
}