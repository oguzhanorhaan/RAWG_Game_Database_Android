package com.oguzhanorhan.rawggamedatabaseandroid.scenes.gamedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.oguzhanorhan.rawggamedatabaseandroid.common.BaseVM
import com.oguzhanorhan.rawggamedatabaseandroid.data.model.RawgApiStatus
import com.oguzhanorhan.rawggamedatabaseandroid.datasource.model.Game
import com.oguzhanorhan.rawggamedatabaseandroid.datasource.remote.getApiKey
import com.oguzhanorhan.rawggamedatabaseandroid.domain.usecase.GameDetailsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GameDetailsVM constructor(
    private var item: Game,
    private val gameDetailsUseCase: GameDetailsUseCase
): BaseVM()  {

    private val _status = MutableLiveData<RawgApiStatus>()

    val status: LiveData<RawgApiStatus>
        get() = _status

    private val _selectedItem = MutableLiveData<Game?>()

    val selectedItem: LiveData<Game?>
        get() = _selectedItem

    init {
        getGameDetails()
    }

    private fun getGameDetails() {
        _status.value = RawgApiStatus.LOADING
        launch {
            val response = gameDetailsUseCase.get(getApiKey(), item.id!!)
            withContext(Dispatchers.Main) {
                _status.value = response.status
                when(_status.value) {
                    RawgApiStatus.DONE -> {
                        _selectedItem.value = response.data
                    }
                    else -> {
                        _selectedItem.value = Game()
                    }
                }
            }
        }
    }
}