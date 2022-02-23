package com.oguzhanorhan.rawggamedatabaseandroid.common

import com.oguzhanorhan.rawggamedatabaseandroid.data.datasource.RemoteDataSource
import com.oguzhanorhan.rawggamedatabaseandroid.data.repository.RawgLocalRepositoryImpl
import com.oguzhanorhan.rawggamedatabaseandroid.data.repository.RawgRepositoryImpl
import com.oguzhanorhan.rawggamedatabaseandroid.datasource.remote.RawgAPI
import com.oguzhanorhan.rawggamedatabaseandroid.datasource.remote.RemoteDataSourceImpl
import com.oguzhanorhan.rawggamedatabaseandroid.datasource.remote.ResponseHandler
import com.oguzhanorhan.rawggamedatabaseandroid.datasource.remote.createNetworkClient
import com.oguzhanorhan.rawggamedatabaseandroid.datasource.remote.model.Game
import com.oguzhanorhan.rawggamedatabaseandroid.datasource.room.db.databaseClient
import com.oguzhanorhan.rawggamedatabaseandroid.domain.repository.RawgLocalRepository
import com.oguzhanorhan.rawggamedatabaseandroid.domain.repository.RawgRepository
import com.oguzhanorhan.rawggamedatabaseandroid.domain.usecase.*
import com.oguzhanorhan.rawggamedatabaseandroid.scenes.favouritegames.FavouriteGamesVM
import com.oguzhanorhan.rawggamedatabaseandroid.scenes.gamedetails.GameDetailsVM
import com.oguzhanorhan.rawggamedatabaseandroid.scenes.gamelist.GameListVM
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
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
            retrieveGameListAndSaveToLocaleUseCase = get(),
            searchGameLocaleUseCase = get()
        )
    }
    viewModel { (item: Game) ->
        GameDetailsVM(
            item = item,
            gameDetailsUseCase = get(),
            gameFavouriteStatusUseCase = get(),
            updateGameFavouriteStatusUseCase = get()
        )
    }
    viewModel {
        FavouriteGamesVM(
            getFavouritesUseCase = get()
        )
    }
}

val useCaseModule: Module = module {
    factory {
        RetrieveGameListAndSaveToLocaleUseCase(
            rawgRepository = get(),
            rawgLocaleRepository = get()
        )
    }
    factory { GameDetailsUseCase(rawgRepository = get()) }
    factory { SearchGameLocaleUseCase(rawgLocaleRepository = get()) }
    factory { GetFavouritesUseCase(rawgLocaleRepository = get()) }
    factory { GameFavouriteStatusUseCase(rawgLocaleRepository = get()) }
    factory { UpdateGameFavouriteStatusUseCase(rawgLocaleRepository = get()) }
}

val repositoryModule: Module = module {
    single { RawgRepositoryImpl(remoteDataSource = get(), responseHandler = get()) as RawgRepository }
    single { RawgLocalRepositoryImpl(db = get()) as RawgLocalRepository }
}

val dataSourceModule: Module = module {
    single { RemoteDataSourceImpl(api = rawgAPI) as RemoteDataSource }
}

val networkModule: Module = module {
    single { rawgAPI }
    single { ResponseHandler() }
    single { databaseClient(context = androidContext())?.gameDao() }
}

private val retrofit: Retrofit = createNetworkClient(Configs.Networking.BaseUrl)

private val rawgAPI: RawgAPI = retrofit.create(RawgAPI::class.java)
