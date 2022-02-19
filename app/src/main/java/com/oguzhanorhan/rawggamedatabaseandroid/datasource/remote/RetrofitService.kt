package com.oguzhanorhan.rawggamedatabaseandroid.datasource.remote

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.oguzhanorhan.rawggamedatabaseandroid.datasource.model.Games
import com.oguzhanorhan.rawggamedatabaseandroid.datasource.model.GamesResults
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RawgAPI {
    @GET("games")
    suspend fun getGenresDetail(
        @Query("key") key: String?,
        @Query("genres") genre: Int,
        @Query("ordering") ordering: String?,
        @Query("page") page: Int,
        @Query("page_size") page_size: Int
    ): Response<Games>

    @GET("games/{id}")
    suspend fun getGameDetail(
        @Path("id") id: Int,
        @Query("key") key: String?
    ): Response<GamesResults>
}

private fun configureClient(): OkHttpClient {
    val interceptor = HttpLoggingInterceptor()
    interceptor.apply { interceptor.level = HttpLoggingInterceptor.Level.BODY }
    return OkHttpClient.Builder().addInterceptor(interceptor).build()
}

fun createNetworkClient(baseUrl: String) = retrofitClient(baseUrl)

private fun retrofitClient(baseUrl: String): Retrofit = Retrofit.Builder()
    .client(configureClient())
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(baseUrl)
    .build()

fun getApiKey(): String {
    return "059e8e95ff7c41829c55085d9954dc6b"
}