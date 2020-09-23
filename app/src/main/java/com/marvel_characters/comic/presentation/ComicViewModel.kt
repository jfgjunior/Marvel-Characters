package com.marvel_characters.comic.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.marvel_characters.comic.data.models.Comic
import com.marvel_characters.comic.di.ComicComponent.Companion.CHARACTER_ID_NAME
import com.marvel_characters.comic.domain.usecases.FindMostExpensiveCharacterComic
import com.marvel_characters.common.domain.UseCase
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Named

private const val TAG = "ComicViewModel"

class ComicViewModel @Inject constructor(
    findMostExpensiveCharacterComic: UseCase<@JvmSuppressWildcards FindMostExpensiveCharacterComic.Param,
            @JvmSuppressWildcards Single<@JvmSuppressWildcards Comic>>,
   @Named(CHARACTER_ID_NAME) characterId: Int
) : ViewModel() {

    private val disposable: Disposable
    private val _comic = MutableLiveData<Comic>()
    val comic: LiveData<Comic>
        get() = _comic
    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean>
        get() = _error

    init {
        disposable =
            findMostExpensiveCharacterComic.run(FindMostExpensiveCharacterComic.Param(characterId))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _comic.value = it
                }, {
                    Log.e(TAG, "error fetching comic price", it)
                    _error.value = true
                })
    }

    fun onDestroy() {
        disposable.dispose()
    }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }
}