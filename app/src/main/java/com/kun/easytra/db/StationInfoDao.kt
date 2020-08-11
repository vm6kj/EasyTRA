package com.kun.easytra.db

import androidx.room.Dao

@Dao
interface StationInfoDao {
    fun getAll(): List<StationInfo>
}