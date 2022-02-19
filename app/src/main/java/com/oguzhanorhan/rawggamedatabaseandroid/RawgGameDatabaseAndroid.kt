package com.oguzhanorhan.rawggamedatabaseandroid

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class RawgGameDatabaseAndroid : Application()  {

    override fun onCreate() {
        super.onCreate()
        startKoin { androidContext(this@RawgGameDatabaseAndroid) }
    }
}