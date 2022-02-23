package com.oguzhanorhan.rawggamedatabaseandroid.datasource.remote

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.oguzhanorhan.rawggamedatabaseandroid.datasource.remote.model.Game
import com.oguzhanorhan.rawggamedatabaseandroid.datasource.remote.model.Games
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RawgAPI {
    @GET("games")
    fun getGames(
        @Query("key") key: String?
    ): Deferred<Games>

    @GET("games/{id}")
    fun getGameDetail(
        @Path("id") id: Int,
        @Query("key") key: String?
    ): Deferred<Game>
}

private fun configureClient(): OkHttpClient {
    val interceptor = HttpLoggingInterceptor()
    interceptor.apply { interceptor.level = HttpLoggingInterceptor.Level.BODY }
    return OkHttpClient.Builder().addInterceptor(interceptor).build()
}

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

fun createNetworkClient(baseUrl: String) = retrofitClient(baseUrl)

private fun retrofitClient(baseUrl: String): Retrofit = Retrofit.Builder()
    .client(configureClient())
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(baseUrl)
    .build()

fun getApiKey(): String {
    return "059e8e95ff7c41829c55085d9954dc6b"
}