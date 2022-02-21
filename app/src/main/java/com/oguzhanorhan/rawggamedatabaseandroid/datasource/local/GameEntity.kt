package com.oguzhanorhan.rawggamedatabaseandroid.datasource.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.oguzhanorhan.rawggamedatabaseandroid.datasource.model.Game

@Entity(tableName = "game_table")
class GameEntity (
    @PrimaryKey(autoGenerate = true)var id: Int?,
    var name: String?,
    var rating: Float?,
    var released: String?,
    var background: String?,
)

fun GameEntity.mapToDomain(): Game = Game(id = id, name =  name, rating = rating, released = released, background_image = background)