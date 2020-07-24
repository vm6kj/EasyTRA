package com.kun.easytra.ui.allstation.paging

import androidx.paging.PagingSource
import com.kun.easytra.tradata.responsebody.StationInfo

@Deprecated("Not in used. Usage of paging 3")
class StationInfoPagingSource : PagingSource<Int, StationInfo.StationInfoItem>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, StationInfo.StationInfoItem> {
        TODO("Not yet implemented")
    }
}