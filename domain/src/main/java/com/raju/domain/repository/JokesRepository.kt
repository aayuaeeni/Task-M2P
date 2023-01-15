package com.raju.domain.repository

import com.raju.domain.model.jokes.JokesContent
import io.reactivex.rxjava3.core.Observable

interface JokesRepository {
    fun getJokes(
    ): Observable<JokesContent>

}