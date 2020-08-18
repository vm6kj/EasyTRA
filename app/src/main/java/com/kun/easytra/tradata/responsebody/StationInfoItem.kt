package com.kun.easytra.tradata.responsebody

import com.google.gson.annotations.SerializedName

data class StationInfoItem(
    @SerializedName("OperatorID")
    val operatorID: String?,
    @SerializedName("StationAddress")
    val stationAddress: String?,
    @SerializedName("StationClass")
    val stationClass: String?,
    @SerializedName("StationID")
    val stationID: String?,
    @SerializedName("StationName")
    val stationName: StationName?,
    @SerializedName("StationPhone")
    val stationPhone: String?,
    @SerializedName("StationPosition")
    val stationPosition: StationPosition?,
    @SerializedName("StationUID")
    val stationUID: String?,
    @SerializedName("UpdateTime")
    val updateTime: String?,
    @SerializedName("VersionID")
    val versionID: Int?
) {
    data class StationName(
        @SerializedName("En")
        val en: String?,
        @SerializedName("Zh_tw")
        val zh_tw: String?
    )

    data class StationPosition(
        @SerializedName("PositionLat")
        val positionLat: Double?,
        @SerializedName("PositionLon")
        val positionLon: Double?
    )
}