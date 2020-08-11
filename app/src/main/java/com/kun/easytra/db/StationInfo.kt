package com.kun.easytra.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class StationInfo(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "station_id") val stationId: String?,
    @ColumnInfo(name = "station_name_zh") val stationNameZh: String?,
    @ColumnInfo(name = "station_name_en") val stationNameEn: String?
)