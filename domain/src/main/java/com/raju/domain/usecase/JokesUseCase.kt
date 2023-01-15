package com.raju.domain.usecase

import com.raju.domain.model.jokes.JokesContent
import com.raju.domain.repository.JokesRepository
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class JokesUseCase @Inject constructor(private val jokesRepository: JokesRepository) {

    fun getJokes(): Observable<JokesContent> = jokesRepository.getJokes()
}