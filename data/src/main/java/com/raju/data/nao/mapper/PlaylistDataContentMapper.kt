package com.raju.data.nao.mapper

import com.raju.data.nao.playlist.PlaylistDataNao
import com.raju.domain.model.playlist.PlaylistDataContent
import com.raju.domain.parseProtection
import javax.inject.Inject

class PlaylistDataContentMapper @Inject constructor(
    private val resultContentMapper: ResultContentMapper,
) {
    fun map(it: PlaylistDataNao): PlaylistDataContent? {
        return parseProtection {
            PlaylistDataContent(
                it.resultCount,
                it.resultNaos.mapNotNull { result -> resultContentMapper.map(result) }
            )
        }
    }
}