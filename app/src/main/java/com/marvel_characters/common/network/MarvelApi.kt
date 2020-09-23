package com.marvel_characters.common.network

import com.marvel_characters.home.data.models.CharacterDataWrapper
import com.marvel_characters.comic.data.models.ComicDataWrapper
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelApi {

    companion object {
        const val URL = "https://gateway.marvel.com/"

        private const val ORDER_BY_QUERY = "orderBy"
        private const val FORMAT_QUERY = "format"
        private const val FORMAT_TYPE_QUERY = "formatType"
        private const val LIMIT_QUERY = "limit"
        private const val OFFSET_QUERY = "offset"

        private const val CHARACTER_ID_PATH = "characterId"
    }

    @GET("v1/public/characters")
    fun characters(
        @Query(ORDER_BY_QUERY) orderBy: String,
        @Query(LIMIT_QUERY) limit: Int,
        @Query(OFFSET_QUERY) offset: Int
    ): Single<CharacterDataWrapper>

    @GET("v1/public/characters/{$CHARACTER_ID_PATH}/comics")
    fun comics(
        @Path(CHARACTER_ID_PATH) characterId: Int,
        @Query(OFFSET_QUERY) offset: Int,
        @Query(FORMAT_QUERY) format: String,
        @Query(FORMAT_TYPE_QUERY) formatType: String,
        @Query(LIMIT_QUERY) limit: Int
    ): Observable<ComicDataWrapper>
}