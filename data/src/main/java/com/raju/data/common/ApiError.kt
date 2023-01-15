package com.raju.data.common

import com.google.gson.annotations.SerializedName

class ApiError(
    @SerializedName("errorCode")
    val serverErrorCode: String,
    @SerializedName("errMsg")
    val userError: String,
    @SerializedName("devMessage")
    val developerError: String?
) {
}