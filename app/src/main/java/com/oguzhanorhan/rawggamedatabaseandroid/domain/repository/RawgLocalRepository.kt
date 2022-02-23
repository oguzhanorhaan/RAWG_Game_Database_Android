package com.oguzhanorhan.rawggamedatabaseandroid.domain.repository

import com.oguzhanorhan.rawggamedatabaseandroid.datasource.room.model.GameEntity
import kotlinx.coroutines.flow.Flow

interface RawgLocalRepository {

    fun getGames(query: String): Flow<List<GameEntity>>

    fun getAllFavourites(): Flow<List<GameEntity>>

    suspend fun getGame(id: Int): GameEntity

    suspend fun insertGame(game: GameEntity)

    suspend fun updateGame(game: GameEntity)

    suspend fun deleteGame(id: Int)
}