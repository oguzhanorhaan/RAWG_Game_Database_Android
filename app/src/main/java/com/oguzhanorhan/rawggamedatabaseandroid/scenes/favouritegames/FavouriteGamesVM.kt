package com.oguzhanorhan.rawggamedatabaseandroid.scenes.favouritegames

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.oguzhanorhan.rawggamedatabaseandroid.common.BaseVM
import com.oguzhanorhan.rawggamedatabaseandroid.datasource.model.Game
import com.oguzhanorhan.rawggamedatabaseandroid.domain.usecase.GetFavouritesUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class FavouriteGamesVM constructor(
    private val getFavouritesUseCase: GetFavouritesUseCase
): BaseVM() {

    val favouriteGames: LiveData<List<Game?>>
        get() = _favouriteGames

    private val _favouriteGames = MutableLiveData<List<Game?>>()

    init {
        launch {
            getFavouritesUseCase.get().catch {
                Log.d("error", it.localizedMessage)
                _favouriteGames.postValue(ArrayList())
            }.collect {
                _favouriteGames.postValue(it)
            }
        }
    }
}