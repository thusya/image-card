package com.thusee.imagecard.ui.util

import android.content.Context
import com.thusee.imagecard.R
import retrofit2.HttpException
import java.net.UnknownHostException

object ErrorHandler {
    fun getErrorMessage(context: Context, exception: Throwable): String {
        return when (exception) {
            is UnknownHostException -> context.getString(R.string.error_no_network)
            is HttpException -> when {
                exception.message?.contains("404") == true -> context.getString(R.string.error_page_not_found)
                exception.message?.contains("500") == true -> context.getString(R.string.error_server_down)
                else -> context.getString(R.string.error_common)
            }

            else -> context.getString(R.string.error_common)
        }
    }
}