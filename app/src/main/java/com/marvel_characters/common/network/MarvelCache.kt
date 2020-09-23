package com.marvel_characters.common.network

import com.marvel_characters.comic.data.models.Comic
import com.marvel_characters.home.data.models.CharacterDataWrapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MarvelCache @Inject constructor() : Cache {

    private val cache = mutableMapOf<String, Any?>()

    override fun getCharactersPage(page: Int): CharacterDataWrapper? {
        val key = getKey(CHARACTER_KEY, page)
        if (cache.containsKey(key)) {
            return cache[key] as CharacterDataWrapper
        }
        return null
    }

    override fun getComic(characterId: Int): Comic? {
        val key = getKey(COMIC_KEY, characterId)
        if (cache.containsKey(key)) {
            return cache[key] as Comic
        }
        return null
    }

    override fun addCharacterPage(page: Int, characterChunk: CharacterDataWrapper) {
        val key = getKey(CHARACTER_KEY, page)
        cache[key] = characterChunk
    }

    override fun addComic(characterId: Int, comic: Comic?) {
        val key = getKey(COMIC_KEY, characterId)
        cache[key] = comic
    }

    private fun getKey(base: String, identifier: Int) = "$base.$identifier"

    companion object {
        private const val CHARACTER_KEY = "character_key"
        private const val COMIC_KEY = "comic_key"
    }
}