package com.marvel_characters.comic.data.models

import com.marvel_characters.common.data.models.Image
import org.junit.Assert
import org.junit.Test

class ComicModelsTest {

    private val prices = listOf(
        ComicPrice("", 1f),
        ComicPrice("", 2f),
        ComicPrice("", 3f),
        ComicPrice("", 4f)
    )

    private val comic = Comic(1, "title", Image("",""), "description", prices)

    @Test
    fun  `check if comic test gets the highest price`() {
        val result = comic.highestPriceOrEmpty.price
        val expected = prices.last().price
        Assert.assertEquals(expected, result)
    }

    @Test
    fun `check comic price comparison`() {
        var result = comic.prices[0].compareTo(comic.prices[1])
        var expected = -1
        Assert.assertEquals(expected, result)

        result = comic.prices[1].compareTo(comic.prices[0])
        expected = 1
        Assert.assertEquals(expected, result)

        result = comic.prices[0].compareTo(comic.prices[0])
        expected = 0
        Assert.assertEquals(expected, result)
    }
}