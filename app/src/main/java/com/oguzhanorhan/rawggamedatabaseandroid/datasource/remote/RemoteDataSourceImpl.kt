package com.oguzhanorhan.rawggamedatabaseandroid.datasource.remote

import com.oguzhanorhan.rawggamedatabaseandroid.data.datasource.RemoteDataSource
import com.oguzhanorhan.rawggamedatabaseandroid.datasource.model.Games

class RemoteDataSourceImpl constructor(
    private val api: RawgAPI
) : RemoteDataSource {

    override suspend fun getGameList(apiKey: String): Games {
        return api.getGames(apiKey).await()
    }
}