package com.oguzhanorhan.rawggamedatabaseandroid.domain.usecase

import com.oguzhanorhan.rawggamedatabaseandroid.domain.repository.RawgLocalRepository

class GameFavouriteStatusUseCase(
    private val rawgLocaleRepository: RawgLocalRepository
) {

    suspend fun get(id: Int): Boolean {
        return rawgLocaleRepository.getGame(id).isFavourite
    }
}
