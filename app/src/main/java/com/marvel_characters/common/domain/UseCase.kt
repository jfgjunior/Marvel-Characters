package com.marvel_characters.common.domain

interface UseCase<in T, out V> {
    fun run(param: T): V
}