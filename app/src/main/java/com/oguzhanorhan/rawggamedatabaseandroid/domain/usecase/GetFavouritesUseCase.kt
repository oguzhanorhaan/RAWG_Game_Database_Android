package com.oguzhanorhan.rawggamedatabaseandroid.domain.usecase

import com.oguzhanorhan.rawggamedatabaseandroid.datasource.remote.model.Game
import com.oguzhanorhan.rawggamedatabaseandroid.datasource.room.model.mapToDomain
import com.oguzhanorhan.rawggamedatabaseandroid.domain.repository.RawgLocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetFavouritesUseCase(private val rawgLocaleRepository: RawgLocalRepository) {

    suspend fun get(): Flow<List<Game>> {
        return rawgLocaleRepository.getAllFavourites().map { entityList ->
            val list = ArrayList<Game>()
            entityList.forEach { entity ->
                list.add(entity.mapToDomain())
            }
            return@map list
        }
    }
}
