package com.oguzhanorhan.rawggamedatabaseandroid.domain.usecase

import com.oguzhanorhan.rawggamedatabaseandroid.datasource.remote.model.Game
import com.oguzhanorhan.rawggamedatabaseandroid.datasource.room.model.mapToDomain
import com.oguzhanorhan.rawggamedatabaseandroid.domain.repository.RawgLocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.map

class SearchGameLocaleUseCase constructor(
    private val rawgLocaleRepository: RawgLocalRepository
) {

    suspend fun get(query: String, ): Flow<List<Game>> {
        return rawgLocaleRepository.getGames(query).debounce(300).map { entityList ->
            val list = ArrayList<Game>()
            entityList.forEach { entity ->
                list.add(entity.mapToDomain())
            }
            return@map list
        }
    }
}