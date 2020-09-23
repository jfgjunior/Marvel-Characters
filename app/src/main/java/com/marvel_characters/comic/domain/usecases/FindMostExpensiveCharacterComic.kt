package com.marvel_characters.comic.domain.usecases

import com.marvel_characters.comic.data.models.Comic
import com.marvel_characters.comic.domain.repository.ComicRepository
import com.marvel_characters.common.domain.UseCase
import com.marvel_characters.common.utils.COMICS_PER_REQUEST
import com.marvel_characters.common.utils.DEFAULT_FORMAT
import com.marvel_characters.common.utils.DEFAULT_FORMAT_TYPE
import io.reactivex.Single
import javax.inject.Inject

class FindMostExpensiveCharacterComic @Inject constructor(
    private val comicRepository: ComicRepository
) : UseCase<FindMostExpensiveCharacterComic.Param, Single<Comic>> {

    override fun run(param: Param): Single<Comic> {
        return comicRepository.getMostExpensiveComic(
            param.characterId,
            param.format,
            param.formatType,
            param.limit
        )
    }

    data class Param(
        val characterId: Int,
        val format: String = DEFAULT_FORMAT,
        val formatType: String = DEFAULT_FORMAT_TYPE,
        val limit: Int = COMICS_PER_REQUEST
    )
}