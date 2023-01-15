package com.raju.data.repository

import com.raju.data.nao.mapper.JokesContentMapper
import com.raju.data.network.UMApiInterface
import com.raju.data.utils.makeApiRequest
import com.raju.domain.model.jokes.JokesContent
import com.raju.domain.repository.JokesRepository
import com.raju.utils.onErrorObservable
import com.raju.utils.onSuccessObservable
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject
import javax.inject.Named

class JokesDataRepository @Inject constructor(
    @Named("api") private val UMApiInterface: UMApiInterface,
    private val jokesContentMapper: JokesContentMapper,
) : JokesRepository {

    override fun getJokes(): Observable<JokesContent> {
        return makeApiRequest(UMApiInterface.getJokes(
        ), { emitter, it ->
            if (it != null) {
                jokesContentMapper.map(it)
            } else {
                emitter.onErrorObservable(Throwable(message = "Server Error!"))
            }
        }, { emitter, it ->
            if (it is JokesContent) emitter.onSuccessObservable(it)
        })
    }

}