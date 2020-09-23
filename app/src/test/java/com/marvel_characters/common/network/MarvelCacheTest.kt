package com.marvel_characters.common.network

import com.marvel_characters.comic.data.models.ComicDataWrapper
import com.marvel_characters.home.data.models.CharacterDataWrapper
import com.marvel_characters.testutils.JsonConverter
import com.marvel_characters.testutils.JsonConverter.CHARACTER_FILE
import com.marvel_characters.testutils.JsonConverter.COMICS_FILE
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class MarvelCacheTest {
    val marvelCache = MarvelCache()
    lateinit var characterDataWrapper: CharacterDataWrapper
    lateinit var comicDataWrapper: ComicDataWrapper

    @Before
    fun setupTest() {
        characterDataWrapper = JsonConverter.fromJsonToClass(CHARACTER_FILE)
        comicDataWrapper = JsonConverter.fromJsonToClass(COMICS_FILE)
    }

    @Test
    fun `check if characterDataWrapper is saved`() {
        val page = 0
        marvelCache.addCharacterPage(page, characterDataWrapper)
        val expected = characterDataWrapper
        val result = marvelCache.getCharactersPage(page)
        Assert.assertEquals(expected, result)
    }

    @Test
    fun `check if comic is saved`() {
        val id = 0
        marvelCache.addComic(id, comicDataWrapper.data.results[0])
        val expected = comicDataWrapper.data.results[0]
        val result = marvelCache.getComic(id)
        Assert.assertEquals(expected, result)
    }
}