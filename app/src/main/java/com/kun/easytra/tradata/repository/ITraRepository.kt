package com.kun.easytra.tradata.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.kun.easytra.db.model.CityStations
import com.kun.easytra.tradata.responsebody.StationInfoItem
import retrofit2.Response

interface ITraRepository {
    suspend fun getStationInfo(key: Int?, size: Int?): Response<List<StationInfoItem>>

    suspend fun getCityStationInfoFromDb(city: String): CityStations

    suspend fun insertCityStationInfoToDb(cityStationsList: List<CityStations>)

    fun getStationInfoList(): LiveData<PagedList<StationInfoItem>>

    fun getAllCities(context: Context): List<String>
}