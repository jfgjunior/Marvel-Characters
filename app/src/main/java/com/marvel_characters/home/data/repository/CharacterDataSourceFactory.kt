package com.marvel_characters.home.data.repository

import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.marvel_characters.home.data.models.Character
import javax.inject.Inject

class CharacterDataSourceFactory @Inject constructor(
    private val characterDataSource: PageKeyedDataSource<@JvmSuppressWildcards Int,
            @JvmSuppressWildcards Character>
) : DataSource.Factory<Int, Character>() {

    override fun create(): DataSource<Int, Character> {
        return characterDataSource
    }

    fun finalize() {
        (characterDataSource as CharacterDataSource).finalize()
    }
}