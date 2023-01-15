package com.raju.um.presentation.base

import android.util.Log
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    fun logger(tag: String, string: String, e: Throwable? = null) {
        Log.d(tag, string, e)
    }

    override fun onCleared() {
        if (compositeDisposable.isDisposed.not())
            compositeDisposable.dispose()
        super.onCleared()
    }
}