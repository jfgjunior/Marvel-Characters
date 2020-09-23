package com.marvel_characters.comic.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.marvel_characters.comic.data.models.Comic
import com.marvel_characters.comic.data.models.ComicDataWrapper
import com.marvel_characters.comic.domain.usecases.FindMostExpensiveCharacterComic
import com.marvel_characters.common.domain.UseCase
import com.marvel_characters.testutils.JsonConverter
import com.marvel_characters.testutils.getOrAwaitValue
import io.github.plastix.rxschedulerrule.RxJavaRule
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ComicViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var rxRule = RxJavaRule()

    lateinit var viewModel: ComicViewModel
    val dataWrapper: ComicDataWrapper = JsonConverter.fromJsonToClass(JsonConverter.COMICS_FILE)

    @Before
    fun setupTest() {
        val id = dataWrapper.data.results[0].id
        val usecase = object : UseCase<FindMostExpensiveCharacterComic.Param, Single<Comic>> {
            override fun run(param: FindMostExpensiveCharacterComic.Param): Single<Comic> {
                return Single.just(dataWrapper.data.results[0])
            }
        }
        viewModel = ComicViewModel(usecase, id)
    }

    @Test
    fun `verify if live data is called when we get the results`() {
        val value = viewModel.comic.getOrAwaitValue()
        val expected = dataWrapper.data.results[0]
        Assert.assertEquals(expected, value)
    }
}