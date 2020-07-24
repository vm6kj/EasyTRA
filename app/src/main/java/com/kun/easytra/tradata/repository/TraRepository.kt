package com.kun.easytra.tradata.repository

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.kun.easytra.Utils
import com.kun.easytra.tradata.TraApiService
import com.kun.easytra.tradata.responsebody.StationInfo
import com.kun.easytra.ui.allstation.paging.StationDataSourceFactory
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.Response

class TraRepository : ITraRepository, KoinComponent {

    private val traApiService: TraApiService by inject()
    private val stationDataSourceFactory: StationDataSourceFactory by inject()

    private val initialLoadKey = 0
    private val pageSize = 30
    private val config = PagedList.Config.Builder()
        .setPageSize(pageSize)
        .setInitialLoadSizeHint(pageSize)
        .setEnablePlaceholders(false)
        .build()

    companion object {
        private val APPID = "cb9e465dc44141d595d19edbdd6a4dc3"
        private val APPKEY = "-CCwPH-FE0zq1r0ex-sPKtWyREU"

        private lateinit var xDate: String
        private lateinit var signature: String
        private lateinit var auth: String

        private fun refreshAuthentication() {
            xDate = Utils.getServerTimeInXDateFormat().toString()
            signature = Utils.getHmacSha1Signature("x-date: $xDate", APPKEY).toString()
            auth = "hmac username=\"$APPID\", algorithm=\"hmac-sha1\", headers=\"x-date\", signature=\"$signature\""
        }
    }

    init {
        refreshAuthentication()
    }

    override suspend fun getStationInfo(key: Int?, size: Int?): Response<StationInfo> {
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

    override fun getStationInfoList(): LiveData<PagedList<StationInfo.StationInfoItem>> {
        return LivePagedListBuilder(stationDataSourceFactory, config)
            .setInitialLoadKey(initialLoadKey)
            .build()
    }
}