package com.marvel_characters.common.network

import com.marvel_characters.comic.data.models.Comic
import com.marvel_characters.home.data.models.CharacterDataWrapper

interface Cache {
    
    fun getCharactersPage(page: Int): CharacterDataWrapper?
    fun getComic(characterId: Int): Comic?
    fun addCharacterPage(page: Int, characterChunk: CharacterDataWrapper)
    fun addComic(characterId: Int, comic: Comic?)
}