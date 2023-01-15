package com.raju.um.mapper

import com.raju.domain.model.jokes.JokesContent
import com.raju.domain.parseProtection
import com.raju.um.bean.order.JokesBean
import javax.inject.Inject

class JokesBeanMapper @Inject constructor() {
    fun map(it: JokesContent): JokesBean? {
        return parseProtection {
            JokesBean(
                it.jokes
            )
        }
    }
}