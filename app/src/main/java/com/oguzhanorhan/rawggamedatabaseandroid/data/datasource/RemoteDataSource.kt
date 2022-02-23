package com.oguzhanorhan.rawggamedatabaseandroid.data.datasource

import com.oguzhanorhan.rawggamedatabaseandroid.datasource.remote.model.Game
import com.oguzhanorhan.rawggamedatabaseandroid.datasource.remote.model.Games

interface RemoteDataSource {
    suspend fun getGameList(apiKey: String): Games
    suspend fun getGameDetail(apiKey: String, id: Int): Game
}