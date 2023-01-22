package com.raju.domain.repository

import com.raju.domain.model.playlist.PlaylistDataContent
import io.reactivex.rxjava3.core.Observable

interface PlaylistRepository {
    fun getPlaylist(): Observable<PlaylistDataContent>
}