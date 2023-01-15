package com.raju.domain.model

data class StandardError(
    var responseCode: Int? = null,
    val developerMessage: String,
    val userMessage: Int? = null,
    val serverUserMessage: String? = null,
    val serverErrorCode: String? = null,
) : Throwable()

enum class ErrorType {
    CRITICAL, NON_CRITICAL, API
}