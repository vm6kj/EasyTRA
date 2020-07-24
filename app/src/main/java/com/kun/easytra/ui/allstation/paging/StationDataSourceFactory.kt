package com.kun.easytra.ui.allstation.paging

import androidx.paging.DataSource
import com.kun.easytra.NetworkState
import com.kun.easytra.tradata.responsebody.StationInfo

class StationDataSourceFactory : DataSource.Factory<Int, StationInfo.StationInfoItem>() {
    override fun create(): DataSource<Int, StationInfo.StationInfoItem> {
        return StationInfoPageKeyedDataSource(
            initialLoadState = NetworkState.IDLE,
            loadMoreState = NetworkState.IDLE
        )
    }
}