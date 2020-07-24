package com.kun.easytra.tradata

import com.kun.easytra.tradata.responsebody.StationInfo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.QueryMap

interface TraApiService {

    @GET("Rail/TRA/Station?\$orderby=StationID&\$format=JSON")
    @Headers("Accept: application/json")
    suspend fun getStationInfo(
        @Header("Authorization") auth: String,
        @Header("x-date") xDate: String,
        @QueryMap options: Map<String, String>
    ): Response<StationInfo>
}