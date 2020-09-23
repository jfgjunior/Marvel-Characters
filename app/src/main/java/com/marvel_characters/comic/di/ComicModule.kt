package com.marvel_characters.comic.di

import com.marvel_characters.comic.data.models.Comic
import com.marvel_characters.comic.data.repository.ComicRepositoryImpl
import com.marvel_characters.comic.domain.repository.ComicRepository
import com.marvel_characters.comic.domain.usecases.FindMostExpensiveCharacterComic
import com.marvel_characters.common.domain.UseCase
import dagger.Binds
import dagger.Module
import io.reactivex.Single

@Module
interface ComicModule {

    @Binds
    fun bindFindMostExpensiveCharacterComic(
        findMostExpensiveCharacterComic: FindMostExpensiveCharacterComic
    ): UseCase<FindMostExpensiveCharacterComic.Param, Single<Comic>>

    @Binds
    fun bindComicRepository(
        comicRepositoryImpl: ComicRepositoryImpl
    ): ComicRepository
}