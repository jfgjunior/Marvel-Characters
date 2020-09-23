@file:Suppress("UNCHECKED_CAST")

package com.marvel_characters.home.di

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.marvel_characters.MarvelApplication
import com.marvel_characters.di.ApplicationComponent

val Fragment.injector: ApplicationComponent
    get() = (requireActivity().application as MarvelApplication).applicationComponent

/**
 * Provides a lambda function to the ViewModel factory so we "teach" the factory how to
 * create our ViewModel, that way we can pass arguments to the ViewModel constructor and still
 * getting all the support of Android to handle the lifecycle of the ViewModel
 *
 * @param provider function which creates the ViewModel
 */
inline fun <reified T : ViewModel> Fragment.viewModel(
    crossinline provider: () -> T
) = viewModels<T> {
    object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>) = provider() as T
    }
}