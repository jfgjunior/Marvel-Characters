package com.marvel_characters

import android.app.Application
import com.marvel_characters.di.ApplicationComponent
import com.marvel_characters.di.DaggerApplicationComponent

class MarvelApplication : Application() {

    val applicationComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.create()
    }

    override fun onCreate() {
        super.onCreate()
    }
}