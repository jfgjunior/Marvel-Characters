package com.marvel_characters.comic.data.models

import android.os.Parcelable
import com.marvel_characters.common.data.models.Image
import kotlinx.android.parcel.Parcelize

data class ComicDataWrapper(
    val code: Int,
    val data: ComicDataContainer
)

data class ComicDataContainer(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val results: List<Comic>
)

@Parcelize
data class Comic(
    val id: Int,
    val title: String,
    val thumbnail: Image,
    val description: String,
    val prices: List<ComicPrice>
) : Parcelable {
    val highestPriceOrEmpty: ComicPrice
        get() = prices.maxBy { it.price } ?: ComicPrice.empty
}

@Parcelize
data class ComicPrice(
    val type: String,
    val price: Float
) : Comparable<ComicPrice?>, Parcelable {

    override fun compareTo(other: ComicPrice?): Int {
        if (other == null) return 1

        return when {
            price > other.price -> 1
            price < other.price -> -1
            else -> 0
        }
    }

    companion object {
        @JvmStatic
        val empty: ComicPrice
            get() = ComicPrice("", 0F)
    }
}