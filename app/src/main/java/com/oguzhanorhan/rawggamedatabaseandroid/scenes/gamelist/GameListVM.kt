package com.oguzhanorhan.rawggamedatabaseandroid.scenes.gamelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.oguzhanorhan.rawggamedatabaseandroid.common.BaseVM
import com.oguzhanorhan.rawggamedatabaseandroid.data.model.RawgApiStatus
import com.oguzhanorhan.rawggamedatabaseandroid.datasource.model.Game
import com.oguzhanorhan.rawggamedatabaseandroid.datasource.remote.getApiKey
import com.oguzhanorhan.rawggamedatabaseandroid.domain.usecase.GameListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GameListVM constructor(
    private val gameListUseCase: GameListUseCase,
): BaseVM() {

    private val _status = MutableLiveData<RawgApiStatus>()

    val status: LiveData<RawgApiStatus>
        get() = _status

    private val _items = MutableLiveData<List<Game?>?>()

    val items: LiveData<List<Game?>?>
        get() = _items

    val recyclerItems: LiveData<List<Game?>?>
        get() = _recyclerItems

    private val _recyclerItems = MutableLiveData<List<Game?>?>()

    private val _navigateToSelectedItem = MutableLiveData<Game?>()

    val navigateToSelectedItem: LiveData<Game?>
        get() = _navigateToSelectedItem

    fun displayItemDetails(item: Game) {
        _navigateToSelectedItem.value = item
    }

    fun displayItemDetailsComplete() {
        _navigateToSelectedItem.value = null
    }

    init {
        getGameList()
    }

    private fun getGameList() {
        _status.value = RawgApiStatus.LOADING
        launch {
            //todo: map to domain
            val response = gameListUseCase.get(getApiKey())
            withContext(Dispatchers.Main) {
                _status.value = response.status
                when(_status.value) {
                    RawgApiStatus.DONE -> {
                        _items.value = response.data?.results
                        if (_items.value?.size ?: 0 > 3) {
                            _recyclerItems.value = _items.value!!.subList(2,_items.value!!.size-1)
                        }
                    }
                    else -> {
                        _items.value = ArrayList()
                    }
                }
            }
        }
    }
}