package com.raju.m2p.bean.playlist

import java.io.Serializable

data class ResultBean(
    val artistName: String,
    val artworkUrl60: String,
    val previewUrl: String,
    val trackName: String,
    var viewType: Int = 1
) : Serializable