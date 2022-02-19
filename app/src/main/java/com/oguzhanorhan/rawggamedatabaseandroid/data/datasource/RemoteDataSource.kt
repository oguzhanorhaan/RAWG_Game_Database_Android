package com.oguzhanorhan.rawggamedatabaseandroid.data.datasource

import com.oguzhanorhan.rawggamedatabaseandroid.datasource.model.Games

interface RemoteDataSource {

    suspend fun getGameList(apiKey: String): Games
}