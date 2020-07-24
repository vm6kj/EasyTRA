package com.kun.easytra.tradata.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.kun.easytra.tradata.responsebody.StationInfo
import retrofit2.Response

interface ITraRepository {
    suspend fun getStationInfo(key: Int?, size: Int?): Response<StationInfo>

    fun getStationInfoList(): LiveData<PagedList<StationInfo.StationInfoItem>>
}