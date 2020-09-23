package com.marvel_characters.home.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.marvel_characters.home.data.models.Character
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    dataSourceFactory: DataSource.Factory<@JvmSuppressWildcards Int,@JvmSuppressWildcards Character>
) : ViewModel() {

    val characterList: LiveData<PagedList<Character>>

    init {
        val pagedListConfig = PagedList.Config.Builder()
            .setPageSize(1)
            .setEnablePlaceholders(true)
            .build()
        characterList = LivePagedListBuilder(dataSourceFactory, pagedListConfig).build()
    }
}