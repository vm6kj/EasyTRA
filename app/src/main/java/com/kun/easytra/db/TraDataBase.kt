package com.kun.easytra.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [CityStations::class], version = 1)
@TypeConverters(TraTypeConverters::class)
abstract class TraDataBase: RoomDatabase() {
    abstract fun stationInfoDao(): StationInfoDao
}