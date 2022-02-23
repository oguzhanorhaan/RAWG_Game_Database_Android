package com.oguzhanorhan.rawggamedatabaseandroid.domain.usecase

import com.oguzhanorhan.rawggamedatabaseandroid.data.model.Resource
import com.oguzhanorhan.rawggamedatabaseandroid.datasource.remote.model.Games
import com.oguzhanorhan.rawggamedatabaseandroid.datasource.room.model.GameEntity
import com.oguzhanorhan.rawggamedatabaseandroid.domain.repository.RawgLocalRepository
import com.oguzhanorhan.rawggamedatabaseandroid.domain.repository.RawgRepository

class RetrieveGameListAndSaveToLocaleUseCase(
    private val rawgRepository: RawgRepository,
    private val rawgLocaleRepository: RawgLocalRepository
) {

    suspend fun get(apiKey: String): Resource<Games> =
        rawgRepository.getGameList(apiKey).apply {
            this.data?.let {
                it.results?.forEach { gameResponse ->
                    rawgLocaleRepository.insertGame(
                        GameEntity(
                            id = gameResponse.id,
                            name = gameResponse.name,
                            rating = gameResponse.rating,
                            released = gameResponse.formattedDate,
                            background = gameResponse.background_image
                        )
                    )
                }
            }
        }
}
