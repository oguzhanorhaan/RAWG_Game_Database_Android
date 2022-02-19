package com.oguzhanorhan.rawggamedatabaseandroid.data.repository

import com.oguzhanorhan.rawggamedatabaseandroid.data.datasource.RemoteDataSource
import com.oguzhanorhan.rawggamedatabaseandroid.data.model.Resource
import com.oguzhanorhan.rawggamedatabaseandroid.datasource.model.Games
import com.oguzhanorhan.rawggamedatabaseandroid.datasource.remote.ResponseHandler
import com.oguzhanorhan.rawggamedatabaseandroid.domain.repository.RawgRepository

class RawgRepositoryImpl (
    private val remoteDataSource: RemoteDataSource,
    private val responseHandler: ResponseHandler
) : RawgRepository {

    override suspend fun getGameList(apiKey: String): Resource<Games> {
        return try {
            val response = remoteDataSource.getGameList(apiKey)
            return responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }
}