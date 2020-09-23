package com.marvel_characters.testutils

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import java.io.File

object JsonConverter {

    inline fun <reified T> fromJsonToClass(fileName: String): T {
        val absolutePath = File("").absolutePath
        val filePath = "$absolutePath/src/test/java/com/resources/$fileName"
        val moshi = Moshi.Builder().build()
        val adapter: JsonAdapter<T> = moshi.adapter(T::class.java)
        val myjson = File(filePath).readText()

        return adapter.fromJson(myjson)!!
    }

    const val CHARACTER_FILE = "character.json"
    const val COMICS_FILE = "comics.json"
}