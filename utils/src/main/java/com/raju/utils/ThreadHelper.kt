package com.raju.utils

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers


object ThreadHelper {

    fun <T> sequentialExecute(
        codeRun: () -> T,
        onResult: ((t: T) -> Unit)? = null,
        onError: ((e: Throwable) -> Unit)? = null
    ): Disposable {
        return observable<T> {
            it.get()?.onNext(codeRun.invoke())
        }.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.single()).subscribe({
                onResult?.invoke(it)
            }, {
                onError?.invoke(it) ?: throw it
            })
    }
}

fun <T> sequentialExecute(
    codeRun: () -> T,
    onResult: ((t: T) -> Unit)? = null,
    onError: ((e: Throwable) -> Unit)? = null
): Disposable {
    return observable<T> {
        it.get()?.onNext(codeRun.invoke())
    }.observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.single()).subscribe({
            onResult?.invoke(it)
        }, {
            onError?.invoke(it) ?: throw it
        })
}

fun <T> compute(
    codeRun: () -> T,
    onResult: ((t: T) -> Unit)? = null,
    onError: ((e: Throwable) -> Unit)? = null
): Disposable {
    return observable<T> {
        it.get()?.onNext(codeRun.invoke())
    }.observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.computation()).subscribe({
            onResult?.invoke(it)
        }, {
            onError?.invoke(it) ?: throw it
        })
}
