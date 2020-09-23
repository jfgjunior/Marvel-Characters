package com.marvel_characters.home.data.repository

import com.marvel_characters.common.network.MarvelApi
import com.marvel_characters.common.network.MarvelCache
import com.marvel_characters.home.data.models.CharacterDataWrapper
import com.marvel_characters.home.domain.repository.CharacterRepository
import com.marvel_characters.testutils.JsonConverter
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class CharacterRepositoryTest {

    lateinit var api: MarvelApi
    lateinit var characterRepository: CharacterRepository
    val dataWrapper: CharacterDataWrapper =
        JsonConverter.fromJsonToClass(JsonConverter.CHARACTER_FILE)

    @Before
    fun setupTest() {
        api = mockk {
            every {
                characters(
                    any(),
                    any(),
                    any()
                )
            } returns Single.just(dataWrapper)
        }
        characterRepository = CharacterRepositoryImpl(MarvelCache(), api)
    }

    @Test
    fun `verify if repository returns the the expected object`() {
        val expected = dataWrapper
        characterRepository.getCharacters(0)
            .observeOn(Schedulers.trampoline())
            .subscribeOn(Schedulers.trampoline())
            .doOnSuccess {
                Assert.assertEquals(expected, it)
            }
            .subscribe({}, {})
    }
}