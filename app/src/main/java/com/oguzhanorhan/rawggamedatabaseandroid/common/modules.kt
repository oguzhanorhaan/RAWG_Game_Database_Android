package com.oguzhanorhan.rawggamedatabaseandroid.common

import com.oguzhanorhan.rawggamedatabaseandroid.datasource.remote.RawgAPI
import com.oguzhanorhan.rawggamedatabaseandroid.datasource.remote.createNetworkClient
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
        GameListVM()
    }
}

val useCaseModule: Module = module {

}

val repositoryModule: Module = module {

}

val dataSourceModule: Module = module {

}

val networkModule: Module = module {

}

// todo: refactor :
// move base_url to config, reach retrofit from companion
private const val BASE_URL = "https://api.rawg.io/api/"

private val retrofit: Retrofit = createNetworkClient(BASE_URL)

private val rawgAPI: RawgAPI = retrofit.create(RawgAPI::class.java)