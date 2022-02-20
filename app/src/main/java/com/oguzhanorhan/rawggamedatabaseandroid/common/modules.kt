package com.oguzhanorhan.rawggamedatabaseandroid.common

import com.oguzhanorhan.rawggamedatabaseandroid.data.datasource.RemoteDataSource
import com.oguzhanorhan.rawggamedatabaseandroid.data.repository.RawgRepositoryImpl
import com.oguzhanorhan.rawggamedatabaseandroid.datasource.remote.RawgAPI
import com.oguzhanorhan.rawggamedatabaseandroid.datasource.remote.RemoteDataSourceImpl
import com.oguzhanorhan.rawggamedatabaseandroid.datasource.remote.ResponseHandler
import com.oguzhanorhan.rawggamedatabaseandroid.datasource.remote.createNetworkClient
import com.oguzhanorhan.rawggamedatabaseandroid.domain.repository.RawgRepository
import com.oguzhanorhan.rawggamedatabaseandroid.domain.usecase.GameListUseCase
import com.oguzhanorhan.rawggamedatabaseandroid.scenes.gamelist.GameListVM
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.android.viewmodel.dsl.viewModel
import retrofit2.Retrofit

fun injectFeature() = loadFeature

private val loadFeature by lazy {
    loadKoinModules(
        listOf(
            viewModelModule,
            useCaseModule,
            repositoryModule,
            dataSourceModule,
            networkModule
        )
    )
}

val viewModelModule: Module = module {
    viewModel {
        GameListVM(
            gameListUseCase = get()
        )
    }
}

val useCaseModule: Module = module {
    factory { GameListUseCase(rawgRepository = get()) }
}

val repositoryModule: Module = module {
    single { RawgRepositoryImpl(remoteDataSource = get(), responseHandler = get()) as RawgRepository}
}

val dataSourceModule: Module = module {
    single { RemoteDataSourceImpl(api = rawgAPI) as RemoteDataSource }
}

val networkModule: Module = module {
    single { rawgAPI }
    single { ResponseHandler() }
}

private val retrofit: Retrofit = createNetworkClient(Configs.Networking.BaseUrl)

private val rawgAPI: RawgAPI = retrofit.create(RawgAPI::class.java)