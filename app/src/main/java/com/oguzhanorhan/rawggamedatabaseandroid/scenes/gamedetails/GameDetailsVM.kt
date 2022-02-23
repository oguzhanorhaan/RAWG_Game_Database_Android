package com.oguzhanorhan.rawggamedatabaseandroid.scenes.gamedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.oguzhanorhan.rawggamedatabaseandroid.common.log.AppEventType
import com.oguzhanorhan.rawggamedatabaseandroid.common.view.BaseVM
import com.oguzhanorhan.rawggamedatabaseandroid.data.model.RawgApiStatus
import com.oguzhanorhan.rawggamedatabaseandroid.datasource.remote.getApiKey
import com.oguzhanorhan.rawggamedatabaseandroid.datasource.remote.model.Game
import com.oguzhanorhan.rawggamedatabaseandroid.domain.usecase.GameDetailsUseCase
import com.oguzhanorhan.rawggamedatabaseandroid.domain.usecase.GameFavouriteStatusUseCase
import com.oguzhanorhan.rawggamedatabaseandroid.domain.usecase.UpdateGameFavouriteStatusUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GameDetailsVM constructor(
    private var item: Game,
    private val gameDetailsUseCase: GameDetailsUseCase,
    private val gameFavouriteStatusUseCase: GameFavouriteStatusUseCase,
    private val updateGameFavouriteStatusUseCase: UpdateGameFavouriteStatusUseCase
) : BaseVM() {

    private val _status = MutableLiveData<RawgApiStatus>()

    val status: LiveData<RawgApiStatus>
        get() = _status

    private val _selectedItem = MutableLiveData<Game?>()

    private val _isItemFavourite = MutableLiveData<Boolean?>()

    val isItemFavourite: LiveData<Boolean?>
        get() = _isItemFavourite

    val selectedItem: LiveData<Game?>
        get() = _selectedItem

    init {
        getGameDetails()
    }

    private fun getGameDetails() {
        _status.value = RawgApiStatus.LOADING
        launch {
            val response = gameDetailsUseCase.get(getApiKey(), item.id!!)
            val favouriteStatus = response.data?.id?.let { gameFavouriteStatusUseCase.get(it) } ?: false
            withContext(Dispatchers.Main) {
                _status.value = response.status
                when (_status.value) {
                    RawgApiStatus.DONE -> {
                        _selectedItem.value = response.data
                        _isItemFavourite.value = favouriteStatus
                    }
                    else -> {
                        _selectedItem.value = Game()
                    }
                }
            }
        }
    }

    fun updateFavourite() {
        _isItemFavourite.postValue(!(_isItemFavourite.value)!!)
        launch {
            selectedItem.value?.id?.let {
                if (_isItemFavourite.value == true) { AppEventType.AddFavourite(it.toString()).send() } else { AppEventType.RemoveFavourite(it.toString()).send() }
                updateGameFavouriteStatusUseCase.get(it)
            }
        }
    }
}
