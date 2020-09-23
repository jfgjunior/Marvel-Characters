package com.marvel_characters.comic.data.repository

import com.marvel_characters.comic.data.models.ComicDataWrapper
import com.marvel_characters.comic.domain.repository.ComicRepository
import com.marvel_characters.common.network.MarvelApi
import com.marvel_characters.common.network.MarvelCache
import com.marvel_characters.testutils.JsonConverter
import com.marvel_characters.testutils.JsonConverter.COMICS_FILE
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ComicRepositoryTest {
    lateinit var api: MarvelApi
    lateinit var comicRepository: ComicRepository
    val dataWrapper: ComicDataWrapper = JsonConverter.fromJsonToClass(COMICS_FILE)

    @Before
    fun setupTest() {
        api = mockk {
            every {
                comics(
                    any(),
                    any(),
                    any(),
                    any(),
                    any()
                )
            } returns Observable.create { emitter ->
                emitter.onNext(dataWrapper)
                emitter.onNext(mockk {
                    every { dataWrapper.data.results } returns listOf()
                })
                emitter.onComplete()
            }

        }
        comicRepository = ComicRepositoryImpl(MarvelCache(), api)
    }

    @Test
    fun `verify if repository returns the most expensive comic`() {
        val id = dataWrapper.data.results[0].id
        val expected = 2.99f
        comicRepository.getMostExpensiveComic(id)
            .observeOn(Schedulers.trampoline())
            .subscribeOn(Schedulers.trampoline())
            .doOnSuccess {
                Assert.assertEquals(expected, it.highestPriceOrEmpty.price)
            }
            .subscribe({}, {})
    }
}