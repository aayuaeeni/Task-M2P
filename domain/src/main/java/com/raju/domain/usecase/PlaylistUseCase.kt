package com.raju.domain.usecase

import com.raju.domain.model.playlist.PlaylistDataContent
import com.raju.domain.repository.PlaylistRepository
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class PlaylistUseCase @Inject constructor(private val playlistRepository: PlaylistRepository) {
    fun getPlaylist(): Observable<PlaylistDataContent> = playlistRepository.getPlaylist()

}