package com.oguzhanorhan.rawggamedatabaseandroid.data.model

data class Resource<out T>(val status: RawgApiStatus, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(RawgApiStatus.DONE, data, null)
        }

        fun <T> error(msg: String, data: T?): Resource<T> {
            return Resource(RawgApiStatus.ERROR, data, msg)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(RawgApiStatus.LOADING, data, null)
        }
    }
}
