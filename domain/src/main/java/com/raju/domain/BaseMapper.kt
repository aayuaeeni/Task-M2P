package com.raju.domain

import com.raju.domain.model.ErrorCodes.App.API_PARSE_EXCEPTION
import com.raju.domain.model.StandardError

fun <T> parseProtection(parser: () -> T?): T? {
    try {
        return parser.invoke()
    } catch (e: Exception) {
        throw StandardError(
            API_PARSE_EXCEPTION,
            e.message ?: "", serverUserMessage = "Something went wrong"
        )
    }

}