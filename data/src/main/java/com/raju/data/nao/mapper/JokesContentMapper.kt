package com.raju.data.nao.mapper

import com.raju.data.nao.JokesData
import com.raju.domain.model.jokes.JokesContent
import com.raju.domain.parseProtection
import javax.inject.Inject

class JokesContentMapper @Inject constructor() {
    fun map(it: JokesData): JokesContent? {
        return parseProtection {
            JokesContent(
                it.joke
            )
        }
    }
}