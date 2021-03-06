package com.oguzhanorhan.rawggamedatabaseandroid.datasource.room.dao

import androidx.room.*
import com.oguzhanorhan.rawggamedatabaseandroid.datasource.room.model.GameEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {

    @Query("Select * from game_table WHERE name LIKE '%' || :query || '%' ORDER BY name DESC")
    fun getGames(query: String): Flow<List<GameEntity>>

    @Query("Select * from game_table WHERE isFavourite = :favourite")
    fun getFavourites(favourite: Boolean = true): Flow<List<GameEntity>>

    @Query("Select * from game_table where id = :id")
    suspend fun getGame(id: Int): GameEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertGame(game: GameEntity)

    @Update
    suspend fun updateGame(game: GameEntity)

    @Query("DELETE FROM game_table where id = :id")
    suspend fun deleteGame(id: Int)
}
