package com.raju.data.utils

import com.raju.data.common.ApiResponse
import com.raju.domain.model.ErrorCodes
import com.raju.domain.model.StandardError
import com.raju.utils.observable
import com.raju.utils.onErrorObservable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableEmitter
import java.io.IOException
import java.lang.ref.SoftReference
import java.net.UnknownHostException


fun <T, W, P> makeApiRequest(
    apiCall: Observable<W>,
    mapper: (emitter: SoftReference<ObservableEmitter<T>>, args: W) -> P,
    onSuccess: (emitter: SoftReference<ObservableEmitter<T>>, data: P) -> Unit
): Observable<T> {
    return observable { parent ->
        apiCall.map {
            mapper.invoke(parent, it)
        }.subscribe({
            onSuccess.invoke(parent, it)
        }, {
            val msg = when (it) {
                is UnknownHostException -> {
                    "Unable to connect to internet"
                }
                is IOException -> {
                    "Unable to connect to internet"
                }
                else -> {
                    "Something went wrong"
                }
            }
            parent.onErrorObservable(
                StandardError(
                    ErrorCodes.App.NO_INTERNET,
                    it.message ?: "",
                    serverUserMessage = msg
                )
            )
        })
    }
}

fun <T, W, P> makeApiRequestV2(
    apiCall: Observable<ApiResponse<W>>,
    mapper: (args: W) -> P,
    onSuccess: (emitter: SoftReference<ObservableEmitter<T>>, data: P) -> Unit
): Observable<T> {
    return observable { parent ->
        apiCall.map {
            if (it.error != null) {
                Pair(
                    null, StandardError(
                        serverErrorCode = it.error.serverErrorCode,
                        developerMessage = it.error.developerError ?: "",
                        serverUserMessage = it.error.userError
                    )
                )
            } else {
                Pair(mapper.invoke(it.data), null)
            }
        }.subscribe({
            if (it.first != null)
                onSuccess.invoke(parent, it.first!!)
            else {
                parent.onErrorObservable(it.second!!)
            }
        }, {
            val msg = when (it) {
                is UnknownHostException -> {
                    "Unable to connect to internet"
                }
                is IOException -> {
                    "Unable to connect to internet"
                }
                else -> {
                    "Something went wrong"
                }
            }
            parent.onErrorObservable(
                StandardError(
                    responseCode = ErrorCodes.App.NO_INTERNET,
                    developerMessage = it.message ?: "",
                    serverUserMessage = msg
                )
            )
        })
    }
}