package com.oguzhanorhan.rawggamedatabaseandroid.common.log

sealed class AppEventType(val key: String ,val param: String) {
    class ItemClicked(private val itemId: String): AppEventType("item_clicked",itemId)
    class AddFavourite(private val itemId: String): AppEventType("add_favourite",itemId)
    class RemoveFavourite(private val itemId: String): AppEventType("remove_favourite",itemId)


    fun send() {
        AppLog.sendEvent(this)
    }
}