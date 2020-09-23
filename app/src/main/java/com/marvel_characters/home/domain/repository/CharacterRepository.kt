package com.marvel_characters.home.domain.repository

import com.marvel_characters.common.utils.CHARS_PER_REQUEST
import com.marvel_characters.common.utils.DEFAULT_ORDER_BY
import com.marvel_characters.home.data.models.CharacterDataWrapper
import io.reactivex.Single

interface CharacterRepository {

    /**
     *  Get the next [CHARS_PER_REQUEST] characters given a page
     *  @param page used to calculate the offset (offset = page * CHAR_PRE_REQUEST).
     *  @param orderBy by default gets data in alphabetical order
     *  @param limit number of characters per request
     */
    fun getCharacters(
        page: Int,
        orderBy: String = DEFAULT_ORDER_BY,
        limit: Int = CHARS_PER_REQUEST
    ): Single<CharacterDataWrapper>
}