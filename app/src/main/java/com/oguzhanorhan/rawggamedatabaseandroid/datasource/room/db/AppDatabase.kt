package com.oguzhanorhan.rawggamedatabaseandroid.datasource.room.db

import android.content.Context

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.oguzhanorhan.rawggamedatabaseandroid.datasource.room.Converters
import com.oguzhanorhan.rawggamedatabaseandroid.datasource.room.dao.GameDao
import com.oguzhanorhan.rawggamedatabaseandroid.datasource.room.model.GameEntity

@Database(entities = [GameEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase() : RoomDatabase(){

    abstract fun gameDao() : GameDao

    companion object{
        var INSTANCE: AppDatabase? = null
    }
}

fun databaseClient(context: Context): AppDatabase? {

    if(AppDatabase.INSTANCE == null){
        synchronized(AppDatabase::class){
            AppDatabase.INSTANCE = Room.databaseBuilder(context.applicationContext,
                AppDatabase::class.java, "RawgGames.db").build()
        }
    }
    return AppDatabase.INSTANCE
}