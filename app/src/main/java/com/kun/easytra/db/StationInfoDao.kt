package com.kun.easytra.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kun.easytra.db.model.CityStations

@Dao
interface StationInfoDao {

    @Query("SELECT * FROM city_stations WHERE city = :city")
    fun getCityStations(city: String): CityStations

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(cityStationsList: List<CityStations>)
}