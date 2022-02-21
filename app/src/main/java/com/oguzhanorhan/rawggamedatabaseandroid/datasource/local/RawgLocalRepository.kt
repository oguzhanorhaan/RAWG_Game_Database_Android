package com.oguzhanorhan.rawggamedatabaseandroid.datasource.local

import android.content.Context
import com.oguzhanorhan.rawggamedatabaseandroid.datasource.model.Game
import kotlinx.coroutines.flow.Flow

class RawgLocalRepository(context: Context) {
    var db: GameDao = AppDatabase.getInstance(context)?.gameDao()!!

     fun getGames(query: String): Flow<List<GameEntity>>{
        return db.getGames(query)
    }

    fun getAllBookmarkId(): Flow<List<Int>> {
        return db.getAllGamesId()
    }

    suspend fun getGame(id: Int): GameEntity{
        return db.getGame(id)
    }

    suspend fun insertGame(game: GameEntity){
        db.insertGame(game)
    }

    suspend fun updateGame(game: GameEntity){
        db.updateGame(game)
    }

    suspend fun deleteGame(id: Int){
        db.deleteGame(id)
    }
}