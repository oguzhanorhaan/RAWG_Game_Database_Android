package com.oguzhanorhan.rawggamedatabaseandroid.domain.usecase

import com.oguzhanorhan.rawggamedatabaseandroid.data.model.Resource
import com.oguzhanorhan.rawggamedatabaseandroid.datasource.model.Games
import com.oguzhanorhan.rawggamedatabaseandroid.domain.repository.RawgRepository

class GameListUseCase(private val rawgRepository: RawgRepository) {

    suspend fun get(apiKey: String): Resource<Games> =
        rawgRepository.getGameList(apiKey)
}