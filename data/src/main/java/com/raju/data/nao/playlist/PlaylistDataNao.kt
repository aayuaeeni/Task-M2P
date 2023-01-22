package com.raju.data.nao.playlist

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PlaylistDataNao(
    @SerializedName("resultCount")
    val resultCount: Int,
    @SerializedName("results")
    val resultNaos: List<ResultNao>
) : Parcelable