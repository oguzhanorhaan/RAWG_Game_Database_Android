package com.oguzhanorhan.rawggamedatabaseandroid.domain.usecase

import com.oguzhanorhan.rawggamedatabaseandroid.datasource.local.RawgLocalRepository
import com.oguzhanorhan.rawggamedatabaseandroid.datasource.local.mapToDomain
import com.oguzhanorhan.rawggamedatabaseandroid.datasource.model.Game
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.map

class SearchGameLocaleUseCase constructor(
    private val rawgLocaleRepository: RawgLocalRepository
) {

    suspend fun get(query: String): Flow<List<Game>> {
        return rawgLocaleRepository.getGames(query).debounce(300).map { entityList ->
            val list = ArrayList<Game>()
            entityList.forEach { entity ->
                list.add(entity.mapToDomain())
            }
            return@map list
        }
    }
}