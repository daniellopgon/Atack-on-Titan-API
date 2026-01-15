package com.example.atackontitanapi.core.presentation.errors

import android.content.Context
import com.example.atackontitanapi.R

interface ErrorAppUI {
    fun getImageError(): Int
    fun getTitleError(): String
    fun getDescriptionError(): String
    fun getActionRetry(): Unit
}

class ConnectionErrorAppUI(
    val context: Context,
    val onClick: (() -> Unit)?
) : ErrorAppUI {
    override fun getImageError() = R.drawable.img_network_error
    override fun getTitleError() = context.getString(R.string.title_error_connection)
    override fun getDescriptionError() = context.getString(R.string.description_error_connection)
    override fun getActionRetry() { onClick?.invoke() }
}

class ServerErrorAppUI(
    val context: Context,
    val onClick: (() -> Unit)?
) : ErrorAppUI {
    override fun getImageError() = R.drawable.img_unknow_error
    override fun getTitleError() = context.getString(R.string.title_error_server)
    override fun getDescriptionError() = context.getString(R.string.description_error_server)
    override fun getActionRetry() { onClick?.invoke() }
}
