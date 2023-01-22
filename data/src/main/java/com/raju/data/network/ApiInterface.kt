package com.raju.data.network

import com.raju.data.nao.playlist.PlaylistDataNao
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("search")
    fun getPlayList(
        @Query("term") userId: String = "jackjohnson",
        @Query("entity") status: String = "musicVideo"
    ): Observable<PlaylistDataNao>
}