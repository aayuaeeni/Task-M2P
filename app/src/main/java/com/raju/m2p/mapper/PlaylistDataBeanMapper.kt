package com.raju.m2p.mapper

import com.raju.domain.model.playlist.PlaylistDataContent
import com.raju.domain.parseProtection
import com.raju.m2p.bean.playlist.PlaylistDataBean
import javax.inject.Inject

class PlaylistDataBeanMapper @Inject constructor(
    private val resultBeanMapper: ResultBeanMapper,
) {
    fun map(it: PlaylistDataContent): PlaylistDataBean? {
        return parseProtection {
            PlaylistDataBean(
                it.resultCount,
                it.result.mapNotNull { result -> resultBeanMapper.map(result) }
            )
        }
    }
}