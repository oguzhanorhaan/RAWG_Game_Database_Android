package com.oguzhanorhan.rawggamedatabaseandroid.domain.repository

import com.oguzhanorhan.rawggamedatabaseandroid.data.model.Resource
import com.oguzhanorhan.rawggamedatabaseandroid.datasource.remote.model.Game
import com.oguzhanorhan.rawggamedatabaseandroid.datasource.remote.model.Games

interface RawgRepository {
    suspend fun getGameList(apiKey: String): Resource<Games>
    suspend fun getGameDetails(apiKey: String, id: Int): Resource<Game>
}
