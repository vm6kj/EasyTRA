package com.kun.easytra.ui.allstation.paging

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.kun.easytra.NetworkState
import com.kun.easytra.tradata.repository.ITraRepository
import com.kun.easytra.tradata.responsebody.StationInfoItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class StationInfoPageKeyedDataSource(
    private var initialLoadState: NetworkState,
    private var loadMoreState: NetworkState
) : PageKeyedDataSource<Int, StationInfoItem>(), KoinComponent {

    private val TAG = "StationInfoDataSource"
    private val traRepository: ITraRepository by inject()
    private val scope: CoroutineScope by inject()
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, StationInfoItem>
    ) {
        val size = params.requestedLoadSize

        Log.i(TAG, "loadInitial -> size=$size")

        scope.launch {
            initialLoadState = NetworkState.LOADING
            val response = traRepository.getStationInfo(0, size)
            initialLoadState = if (response.isSuccessful) {
                val item = response.body() as List<StationInfoItem>
                callback.onResult(item, previousPageKey = 0, nextPageKey = size)
                NetworkState.IDLE
            } else {
                Log.e(TAG, "loadInitial failed, e=" + response.message())
                NetworkState.ERROR
            }
        }
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, StationInfoItem>
    ) {
        if (NetworkState.LOADING == loadMoreState) return

        val prevKey = params.key
        val size = params.requestedLoadSize

        Log.i(TAG, "loadAfter -> prevKey=$prevKey, size=$size")

        val newKey = prevKey + size

        scope.launch {
            loadMoreState = NetworkState.LOADING
            val response = traRepository.getStationInfo(newKey, size)
            loadMoreState = if (response.isSuccessful) {
                val item = response.body() as List<StationInfoItem>
                callback.onResult(item, newKey)
                NetworkState.IDLE
            } else {
                Log.e(TAG, "loadAfter failed, e=" + response.message())
                NetworkState.ERROR
            }
        }
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, StationInfoItem>
    ) {
        // No need to implement
    }
}