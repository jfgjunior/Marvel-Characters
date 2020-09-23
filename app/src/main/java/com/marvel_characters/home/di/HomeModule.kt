package com.marvel_characters.home.di

import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.marvel_characters.common.domain.UseCase
import com.marvel_characters.home.data.models.Character
import com.marvel_characters.home.data.models.CharacterDataWrapper
import com.marvel_characters.home.data.repository.CharacterDataSource
import com.marvel_characters.home.data.repository.CharacterDataSourceFactory
import com.marvel_characters.home.data.repository.CharacterRepositoryImpl
import com.marvel_characters.home.domain.repository.CharacterRepository
import com.marvel_characters.home.domain.usecases.RequestCharacters
import dagger.Binds
import dagger.Module
import io.reactivex.Single

@Module
interface HomeModule {

    @Binds
    fun bindRequestCharacters(
        requestCharacters: RequestCharacters
    ): UseCase<RequestCharacters.Param, Single<CharacterDataWrapper>>

    @Binds
    fun bindCharacterRepository(
        characterRepository: CharacterRepositoryImpl
    ): CharacterRepository

    @Binds
    fun bindCharacterDataSource(
        characterDataSource: CharacterDataSource
    ): PageKeyedDataSource<Int, Character>

    @Binds
    fun bindCharacterDataSourceFactory(
        characterDataSourceFactory: CharacterDataSourceFactory
    ): DataSource.Factory<Int, Character>
}