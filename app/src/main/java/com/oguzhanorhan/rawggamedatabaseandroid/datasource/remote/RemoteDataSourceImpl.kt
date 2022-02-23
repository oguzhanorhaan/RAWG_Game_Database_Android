package com.oguzhanorhan.rawggamedatabaseandroid.datasource.remote

import com.oguzhanorhan.rawggamedatabaseandroid.data.datasource.RemoteDataSource
import com.oguzhanorhan.rawggamedatabaseandroid.datasource.remote.model.Game
import com.oguzhanorhan.rawggamedatabaseandroid.datasource.remote.model.Games

class RemoteDataSourceImpl constructor(
    private val api: RawgAPI
) : RemoteDataSource {

    override suspend fun getGameList(apiKey: String): Games {
        return api.getGames(apiKey).await()
    }

    override suspend fun getGameDetail(apiKey: String, id: Int): Game {
        return api.getGameDetail(id, apiKey).await()
    }
}
