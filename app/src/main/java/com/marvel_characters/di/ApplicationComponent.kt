package com.marvel_characters.di

import com.marvel_characters.comic.di.ComicComponent
import com.marvel_characters.home.di.HomeComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    val homeComponentFactory: HomeComponent.Factory
    val comicComponentFactory: ComicComponent.Factory
}