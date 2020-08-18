package com.kun.easytra.tradata.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.kun.easytra.R
import com.kun.easytra.Utils
import com.kun.easytra.db.CityStations
import com.kun.easytra.db.StationInfoDao
import com.kun.easytra.db.TraDataBase
import com.kun.easytra.tradata.TraApiService
import com.kun.easytra.tradata.responsebody.StationInfoItem
import com.kun.easytra.ui.allstation.paging.StationDataSourceFactory
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.Response

// TODO Implement remote and local repository individually?
class TraRepository : ITraRepository, KoinComponent {

    private val traApiService: TraApiService by inject()
    private val stationDataSourceFactory: StationDataSourceFactory by inject()

    private val stationInfoDao: StationInfoDao by inject()
    private val traDb: TraDataBase by inject()

    companion object {
        private val APPID = "cb9e465dc44141d595d19edbdd6a4dc3"
        private val APPKEY = "-CCwPH-FE0zq1r0ex-sPKtWyREU"

        private lateinit var xDate: String
        private lateinit var signature: String
        private lateinit var auth: String

        private fun refreshAuthentication() {
            xDate = Utils.getServerTimeInXDateFormat().toString()
            signature = Utils.getHmacSha1Signature("x-date: $xDate", APPKEY).toString()
            auth =
                "hmac username=\"$APPID\", algorithm=\"hmac-sha1\", headers=\"x-date\", signature=\"$signature\""
        }
    }

    init {
        refreshAuthentication()
    }

    override suspend fun getStationInfo(key: Int?, size: Int?): Response<List<StationInfoItem>> {
        val options: MutableMap<String, String> = HashMap()
        size?.let {
            options["\$top"] = it.toString()
        }
        key?.let {
            options["\$skip"] = it.toString()
        }
        refreshAuthentication()
        return traApiService.getStationInfo(auth, xDate, options)
    }

    override suspend fun getCityStationInfoFromDb(city: String): CityStations = stationInfoDao.getCityStations(city)

    override suspend fun insertCityStationInfoToDb(cityStationsList: List<CityStations>) {
        stationInfoDao.insertAll(cityStationsList)
    }

    override fun getStationInfoList(): LiveData<PagedList<StationInfoItem>> {
        val initialLoadKey = 0
        val pageSize = 30
        val config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setInitialLoadSizeHint(pageSize)
            .setEnablePlaceholders(false)
            .build()
        return LivePagedListBuilder(stationDataSourceFactory, config)
            .setInitialLoadKey(initialLoadKey)
            .build()
    }

    override fun getAllCities(context: Context): List<String> {
        return context.resources.getStringArray(R.array.city_array).toCollection(ArrayList())
    }
}