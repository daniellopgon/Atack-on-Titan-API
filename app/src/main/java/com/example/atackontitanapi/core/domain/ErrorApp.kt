package com.example.atackontitanapi.core.domain

sealed class ErrorApp : Exception() {
    data object NetworkError : ErrorApp()
    data object ServerError : ErrorApp()
    data object NotFoundError : ErrorApp()
    data object DataParseError : ErrorApp()
}
