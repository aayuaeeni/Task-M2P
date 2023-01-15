package com.raju.data.nao


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class JokesData(
    @SerializedName("joke")
    val joke: String
) : Parcelable