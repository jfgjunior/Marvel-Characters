package com.marvel_characters.common.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Image(
    private val path: String,
    private val extension: String
) : Parcelable {

    //Replace http by https to avoid security issues
    val url: String
        get() = "${path.replace("http://", "https://")}.$extension"
}