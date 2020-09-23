package com.marvel_characters.home.di

import com.marvel_characters.home.presentation.HomeViewModel
import dagger.BindsInstance
import dagger.Subcomponent
import javax.inject.Named

@Subcomponent(modules = [HomeModule::class])
interface HomeComponent {
    val homeViewModel: HomeViewModel

    @Subcomponent.Factory
    interface Factory{
        fun create(
            @BindsInstance @Named(ERROR_CALLBACK_NAME) onErrorCallback: () -> Unit,
            @BindsInstance @Named(SUCCESS_CALLBACK_NAME) onSuccessCallback: () -> Unit
        ): HomeComponent
    }

    companion object {
        const val SUCCESS_CALLBACK_NAME = "onSuccessCallback"
        const val ERROR_CALLBACK_NAME = "onErrorCallback"
    }
}