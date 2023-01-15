package com.raju.um.presentation.jokes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.raju.domain.usecase.JokesUseCase
import com.raju.um.bean.order.JokesBean
import com.raju.um.mapper.JokesBeanMapper
import com.raju.um.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class JokesViewModel @Inject constructor(
    private val jokesUseCase: JokesUseCase,
    private val jokesBeanMapper: JokesBeanMapper,

    ) : BaseViewModel() {


    private val _jokesLD: MutableLiveData<JokesBean> = MutableLiveData()
    val jokesLD: LiveData<JokesBean>
        get() = _jokesLD


    private val _noItemLD: MutableLiveData<Boolean> = MutableLiveData()
    val noItemLD: LiveData<Boolean>
        get() = _noItemLD

    fun init() {
        getJokes()
    }

    private fun getJokes() {
        jokesUseCase.getJokes().map {
            jokesBeanMapper.map(it)
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it != null) {
                    _jokesLD.postValue(it)
                } else {
                    _noItemLD.postValue(true)
                }
            }, {
                logger("API Error", it.message.toString(), it)
            })
    }

}