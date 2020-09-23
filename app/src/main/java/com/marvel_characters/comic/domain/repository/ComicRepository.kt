package com.marvel_characters.comic.domain.repository

import com.marvel_characters.common.utils.COMICS_PER_REQUEST
import com.marvel_characters.common.utils.DEFAULT_FORMAT
import com.marvel_characters.common.utils.DEFAULT_FORMAT_TYPE
import com.marvel_characters.comic.data.models.Comic
import io.reactivex.Single

interface ComicRepository {

    /**
     *  Get the most expensive comic of a given character
     *  @param characterId id of the character.
     *  @param format issue format
     *  @param formatType issue format type
     */
    fun getMostExpensiveComic(
        characterId: Int,
        format: String = DEFAULT_FORMAT,
        formatType: String = DEFAULT_FORMAT_TYPE,
        limit: Int = COMICS_PER_REQUEST
    ): Single<Comic>
}