package com.raju.m2p.presentation.playlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.raju.domain.usecase.PlaylistUseCase
import com.raju.m2p.bean.playlist.ResultBean
import com.raju.m2p.mapper.PlaylistDataBeanMapper
import com.raju.m2p.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class PlaylistViewModel @Inject constructor(
    private val playlistUseCase: PlaylistUseCase,
    private val playlistDataBeanMapper: PlaylistDataBeanMapper,
) : BaseViewModel() {


    private val _playlistLD: MutableLiveData<List<ResultBean>> = MutableLiveData()
    val playlistLD: LiveData<List<ResultBean>>
        get() = _playlistLD


    private val _noItemLD: MutableLiveData<Boolean> = MutableLiveData()
    val noItemLD: LiveData<Boolean>
        get() = _noItemLD

    fun init() {
        getPlaylist()
    }

    private fun getPlaylist() {
        playlistUseCase.getPlaylist().map {
            playlistDataBeanMapper.map(it)
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it?.resultCount != 0) {
                    _playlistLD.postValue(it?.result)
                } else {
                    _noItemLD.postValue(true)
                }
            }, {
                logger("API Error", it.message.toString(), it)
            })
    }

}