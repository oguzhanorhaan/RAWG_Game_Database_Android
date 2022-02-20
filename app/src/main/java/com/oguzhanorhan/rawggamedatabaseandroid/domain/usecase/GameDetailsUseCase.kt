package com.oguzhanorhan.rawggamedatabaseandroid.domain.usecase

import com.oguzhanorhan.rawggamedatabaseandroid.data.model.Resource
import com.oguzhanorhan.rawggamedatabaseandroid.datasource.model.Game
import com.oguzhanorhan.rawggamedatabaseandroid.domain.repository.RawgRepository

class GameDetailsUseCase(private val rawgRepository: RawgRepository) {

    suspend fun get(apiKey: String, id: Int): Resource<Game> =
        rawgRepository.getGameDetails(apiKey, id)
}