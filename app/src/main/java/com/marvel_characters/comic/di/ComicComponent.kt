package com.marvel_characters.comic.di

import com.marvel_characters.comic.presentation.ComicViewModel
import dagger.BindsInstance
import dagger.Subcomponent
import javax.inject.Named

@Subcomponent(modules = [ComicModule::class])
interface ComicComponent {

    val comicViewModel: ComicViewModel

    @Subcomponent.Factory
    interface Factory {
        fun create(
            @BindsInstance @Named(CHARACTER_ID_NAME) characterId: Int
        ): ComicComponent
    }

    companion object {
        const val CHARACTER_ID_NAME = "characterId"
    }
}