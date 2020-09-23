package com.marvel_characters.common.utils

import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

// region TextView
fun TextView.updateTextOrFallback(text: String?, fallback: String) {
    this.text = if (text.isNullOrBlank()) fallback else text
}
// endregion

// region ImageView
fun ImageView.networkImage(url: String, placeholder: Int) {
    Glide.with(this)
        .load(url)
        .placeholder(placeholder)
        .into(this)
}
// endregion
