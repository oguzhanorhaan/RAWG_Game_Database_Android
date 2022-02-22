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

    private val _navigateToSelectedItem = MutableLiveData<Game?>()

    val navigateToSelectedItem: LiveData<Game?>
        get() = _navigateToSelectedItem

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

    fun displayItemDetails(item: Game) {
        _navigateToSelectedItem.value = item
    }

    fun displayItemDetailsComplete() {
        _navigateToSelectedItem.value = null
    }
}