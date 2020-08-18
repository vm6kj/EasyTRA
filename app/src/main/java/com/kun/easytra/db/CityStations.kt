package com.kun.easytra.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kun.easytra.tradata.responsebody.StationInfoItem

@Entity(tableName = "city_stations")
data class CityStations(
    @PrimaryKey @ColumnInfo(name = "city") val city: String,
    @ColumnInfo(name = "stations_info") val stations: List<StationInfoItem>
)