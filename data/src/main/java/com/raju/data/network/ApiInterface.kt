package com.raju.data.network

import com.raju.data.nao.JokesData
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface ApiInterface {
    @GET("api?format=json")
    fun getJokes(): Observable<JokesData>
}