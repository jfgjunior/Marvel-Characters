package com.marvel_characters.comic.data.repository

import com.marvel_characters.comic.domain.repository.ComicRepository
import com.marvel_characters.common.network.MarvelApi
import com.marvel_characters.common.network.MarvelCache
import com.marvel_characters.comic.data.models.Comic
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class ComicRepositoryImpl @Inject constructor(
    private val cache: MarvelCache,
    private val api: MarvelApi
) : ComicRepository {

    override fun getMostExpensiveComic(
        characterId: Int,
        format: String,
        formatType: String,
        limit: Int
    ): Single<Comic> {
        val cachedValue = cache.getComic(characterId)
        if (cachedValue != null) {
            return Single.just(cachedValue)
        }

        /**
         *  This operation goes over all the comics and stops when the api returns an empty list of
         *  comics as a result, indicating that we have passed the last page. Than all the pages
         *  are merged and we can get the most expensive hq.
         */
        return Observable.range(0, Int.MAX_VALUE)
            .concatMap { page -> api.comics(characterId, page * limit, format, formatType, limit) }
            .map { it.data.results }
            .takeUntil { it.isEmpty() }
            .reduce { t1: List<Comic>, t2: List<Comic> ->
                listOf((t1 + t2).maxBy { it.highestPriceOrEmpty }!!)
            }.map { comics ->
                val result = comics.first()
                cache.addComic(characterId, result)
                result
            }.toSingle()
    }
}