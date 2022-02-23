package com.oguzhanorhan.rawggamedatabaseandroid.scenes.gamelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.oguzhanorhan.rawggamedatabaseandroid.common.view.BaseVM
import com.oguzhanorhan.rawggamedatabaseandroid.data.model.RawgApiStatus
import com.oguzhanorhan.rawggamedatabaseandroid.datasource.remote.getApiKey
import com.oguzhanorhan.rawggamedatabaseandroid.datasource.remote.model.Game
import com.oguzhanorhan.rawggamedatabaseandroid.domain.usecase.RetrieveGameListAndSaveToLocaleUseCase
import com.oguzhanorhan.rawggamedatabaseandroid.domain.usecase.SearchGameLocaleUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GameListVM constructor(
    private val retrieveGameListAndSaveToLocaleUseCase: RetrieveGameListAndSaveToLocaleUseCase,
    private val searchGameLocaleUseCase: SearchGameLocaleUseCase
) : BaseVM() {

    private val _status = MutableLiveData<RawgApiStatus>()

    val status: LiveData<RawgApiStatus>
        get() = _status

    private val _items = MutableLiveData<List<Game?>?>()

    val items: LiveData<List<Game?>?>
        get() = _items

    private val _navigateToSelectedItem = MutableLiveData<Game?>()

    val navigateToSelectedItem: LiveData<Game?>
        get() = _navigateToSelectedItem

    val searchQuery = MutableStateFlow("")

    private val searchGameResultFlow = searchQuery.flatMapLatest {
        searchGameLocaleUseCase.get(it)
    }

    val searchGameResult = searchGameResultFlow.asLiveData()

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
            val response = retrieveGameListAndSaveToLocaleUseCase.get(getApiKey())
            withContext(Dispatchers.Main) {
                _status.value = response.status
                when (_status.value) {
                    RawgApiStatus.DONE -> {
                        _items.value = response.data?.results
                    }
                    else -> {
                        _items.value = ArrayList()
                    }
                }
            }
        }
    }
}
