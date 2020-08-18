package com.kun.easytra.ui.allstation.paging

import androidx.paging.DataSource
import com.kun.easytra.NetworkState
import com.kun.easytra.tradata.responsebody.StationInfoItem

class StationDataSourceFactory : DataSource.Factory<Int, StationInfoItem>() {
    override fun create(): DataSource<Int, StationInfoItem> {
        return StationInfoPageKeyedDataSource(
            initialLoadState = NetworkState.IDLE,
            loadMoreState = NetworkState.IDLE
        )
    }
}