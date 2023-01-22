package com.raju.m2p.mapper

import com.raju.domain.model.playlist.ResultContent
import com.raju.domain.parseProtection
import com.raju.m2p.bean.playlist.ResultBean
import javax.inject.Inject

class ResultBeanMapper @Inject constructor() {
    fun map(it: ResultContent): ResultBean? {
        return parseProtection {
            ResultBean(
                it.artistName,
                it.artworkUrl60,
                it.previewUrl,
                it.trackName
            )
        }
    }
}