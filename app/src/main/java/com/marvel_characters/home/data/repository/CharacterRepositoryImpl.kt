package com.marvel_characters.home.data.repository

import com.marvel_characters.common.network.MarvelApi
import com.marvel_characters.common.network.MarvelCache
import com.marvel_characters.home.data.models.CharacterDataWrapper
import com.marvel_characters.home.domain.repository.CharacterRepository
import io.reactivex.Single
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val cache: MarvelCache,
    private val api: MarvelApi
) : CharacterRepository {

    override fun getCharacters(
        page: Int,
        orderBy: String,
        limit: Int
    ): Single<CharacterDataWrapper> {
        val cachedValue = cache.getCharactersPage(page)
        if (cachedValue != null) {
            return Single.just(cachedValue)
        }
        return api.characters(orderBy, limit, page * limit)
            .doOnSuccess { cache.addCharacterPage(page, it) }
    }
}