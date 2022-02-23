package com.oguzhanorhan.rawggamedatabaseandroid.data.repository

import com.oguzhanorhan.rawggamedatabaseandroid.datasource.room.dao.GameDao
import com.oguzhanorhan.rawggamedatabaseandroid.datasource.room.model.GameEntity
import com.oguzhanorhan.rawggamedatabaseandroid.domain.repository.RawgLocalRepository
import kotlinx.coroutines.flow.Flow

class RawgLocalRepositoryImpl(private val db: GameDao) : RawgLocalRepository {

    override fun getGames(query: String): Flow<List<GameEntity>> {
        return db.getGames(query)
    }

    override fun getAllFavourites(): Flow<List<GameEntity>> {
        return db.getFavourites()
    }

    override suspend fun getGame(id: Int): GameEntity {
        return db.getGame(id)
    }

    override suspend fun insertGame(game: GameEntity) {
        db.insertGame(game)
    }

    override suspend fun updateGame(game: GameEntity) {
        db.updateGame(game)
    }

    override suspend fun deleteGame(id: Int) {
        db.deleteGame(id)
    }
}
