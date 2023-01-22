package com.raju.data.nao.mapper

import com.raju.data.nao.playlist.ResultNao
import com.raju.domain.model.playlist.ResultContent
import com.raju.domain.parseProtection
import javax.inject.Inject

class ResultContentMapper @Inject constructor() {
    fun map(it: ResultNao): ResultContent? {
        return parseProtection {
            ResultContent(
                it.artistName,
                it.artworkUrl60,
                it.previewUrl,
                it.trackName
            )
        }
    }
}