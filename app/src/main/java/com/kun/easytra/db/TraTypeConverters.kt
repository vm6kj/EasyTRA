package com.kun.easytra.db

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kun.easytra.tradata.responsebody.StationInfoItem
import org.koin.core.KoinComponent
import org.koin.core.inject

@TypeConverters
class TraTypeConverters: KoinComponent {
    private val gson: Gson by inject()

    @TypeConverter
    fun stationInfoListToStr(stationInfoList: List<StationInfoItem>): String? {
        return gson.toJson(stationInfoList)
    }


    @TypeConverter
    fun strToStationInfoList(str: String?): List<StationInfoItem>? {
        val type = object : TypeToken<List<StationInfoItem>>(){}.type
        return gson.fromJson(str, type)
    }
}