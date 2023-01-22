package com.raju.data.repository

import com.raju.data.nao.mapper.PlaylistDataContentMapper
import com.raju.data.network.M2PApiInterface
import com.raju.data.utils.makeApiRequest
import com.raju.domain.model.playlist.PlaylistDataContent
import com.raju.domain.repository.PlaylistRepository
import com.raju.utils.onSuccessObservable
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject
import javax.inject.Named

class PlaylistDataRepository @Inject constructor(
    @Named("api") private val m2PApiInterface: M2PApiInterface,
    private val playlistDataContentMapper: PlaylistDataContentMapper,
) : PlaylistRepository {

    override fun getPlaylist(): Observable<PlaylistDataContent> {
        return makeApiRequest(m2PApiInterface.getPlayList(
        ), { emitter, it ->
            playlistDataContentMapper.map(it)
        }, { emitter, it ->
            if (it is PlaylistDataContent) emitter.onSuccessObservable(it)
        })
    }

}