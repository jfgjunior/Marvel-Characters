package com.marvel_characters.home.domain.usecases

import com.marvel_characters.common.domain.UseCase
import com.marvel_characters.common.utils.CHARS_PER_REQUEST
import com.marvel_characters.common.utils.DEFAULT_ORDER_BY
import com.marvel_characters.home.data.models.CharacterDataWrapper
import com.marvel_characters.home.domain.repository.CharacterRepository
import io.reactivex.Single
import javax.inject.Inject

class RequestCharacters @Inject constructor(
    private val characterRepository: CharacterRepository
) : UseCase<RequestCharacters.Param, Single<CharacterDataWrapper>> {

    override fun run(param: Param): Single<CharacterDataWrapper> {
        return characterRepository.getCharacters(
            param.page,
            param.orderBy,
            param.limit
        )
    }

    data class Param(
        val page: Int,
        val orderBy: String = DEFAULT_ORDER_BY,
        val limit: Int = CHARS_PER_REQUEST
    )
}