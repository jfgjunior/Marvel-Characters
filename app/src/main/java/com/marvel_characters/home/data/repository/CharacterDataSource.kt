package com.marvel_characters.home.data.repository

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.marvel_characters.common.domain.UseCase
import com.marvel_characters.common.utils.CHARS_PER_REQUEST
import com.marvel_characters.home.data.models.Character
import com.marvel_characters.home.data.models.CharacterDataWrapper
import com.marvel_characters.home.di.HomeComponent.Companion.ERROR_CALLBACK_NAME
import com.marvel_characters.home.di.HomeComponent.Companion.SUCCESS_CALLBACK_NAME
import com.marvel_characters.home.domain.usecases.RequestCharacters
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Named

private const val TAG = "CharacterDataSource"

class CharacterDataSource @Inject constructor(
    private val requestCharacters: UseCase<@JvmSuppressWildcards RequestCharacters.Param,
            @JvmSuppressWildcards Single<@JvmSuppressWildcards CharacterDataWrapper>>,
    @Named(ERROR_CALLBACK_NAME) private val onErrorCallback: () -> Unit,
    @Named(SUCCESS_CALLBACK_NAME) private val onSuccessCallback: () -> Unit
) : PageKeyedDataSource<Int, Character>() {

    private var disposable: Disposable? = null
    private var totalPages = 0
        get() {
            if (field == 0) return field
            return field / CHARS_PER_REQUEST
        }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Character>
    ) {
        disposable = requestCharacters.run(RequestCharacters.Param(0))
            .retryWhen { it.delay(5, TimeUnit.SECONDS) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                val result = sendResultOrTriggerError(response)
                if (result.isNotEmpty()) {
                    onSuccessCallback()
                    callback.onResult(response.data.results, null, 1)
                }
            }, { error ->
                Log.e(TAG, "Failed to load characters", error)
            })

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Character>) {
        if (params.key <= totalPages) {
            disposable = requestCharacters.run(RequestCharacters.Param(params.key))
                .retryWhen { it.delay(5, TimeUnit.SECONDS) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    val result = sendResultOrTriggerError(response)
                    if (result.isNotEmpty()) {
                        callback.onResult(response.data.results, params.key + 1)
                    }
                }, { error ->
                    Log.d(TAG, "Failed to load characters", error)
                })
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Character>) {}

    fun finalize() {
        disposable?.dispose()
    }

    private fun sendResultOrTriggerError(response: CharacterDataWrapper): List<Character> {
        if (response.code != 200) {
            onErrorCallback()
        }
        totalPages = response.data.total
        return response.data.results
    }
}