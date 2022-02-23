package com.oguzhanorhan.rawggamedatabaseandroid.domain.usecase

import com.oguzhanorhan.rawggamedatabaseandroid.domain.repository.RawgLocalRepository

class UpdateGameFavouriteStatusUseCase(
    private val rawgLocaleRepository: RawgLocalRepository
) {

    suspend fun get(id: Int) {
        rawgLocaleRepository.getGame(id)?.let {
            var isFavourite = it.isFavourite
            rawgLocaleRepository.updateGame(it.apply {
                this.isFavourite = !isFavourite
            })
        }
    }
}