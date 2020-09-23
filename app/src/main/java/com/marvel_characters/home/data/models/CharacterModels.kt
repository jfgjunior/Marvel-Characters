package com.marvel_characters.home.data.models

import android.os.Parcelable
import com.marvel_characters.common.data.models.Image
import kotlinx.android.parcel.Parcelize

data class CharacterDataWrapper(
    val code: Int,
    val data: CharacterDataContainer
)

data class CharacterDataContainer(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val results: List<Character>
)

@Parcelize
data class Character(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: Image
) : Parcelable