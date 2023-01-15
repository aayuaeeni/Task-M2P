package com.raju.utils

import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import java.text.SimpleDateFormat
import java.util.*

fun AppCompatImageView.loadImageFromUrl(imageUrl: String?, placeholder: Int) {
    if (imageUrl.isNullOrEmpty()) {
        Glide.with(this)
            .load(placeholder)
            .into(this)
    } else {
        Glide.with(this)
            .load(imageUrl)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .centerCrop()
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(this)
    }
}

fun AppCompatTextView.formatDate(value: String? = null) {
    if (value.isNullOrBlank()) {
        this.text = "-NA-"
    } else {
        val local = Locale("id", "ID")
        val fmt = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)
        val date = fmt.parse(value)
        val dateFormat = SimpleDateFormat("E, dd MMM yyyy", local)
        val stringDate = dateFormat.format(date)
        this.text = stringDate

    }
}