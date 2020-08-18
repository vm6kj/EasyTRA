package com.kun.easytra.ui.allstation.paging

import android.util.Log
import androidx.paging.ItemKeyedDataSource
import com.kun.easytra.NetworkState
import com.kun.easytra.tradata.repository.ITraRepository
import com.kun.easytra.tradata.responsebody.StationInfoItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

@Deprecated(message = "The situation of getting all the station info is not suitable with " +
        "ItemKeyedDataSource. Because the response ids are not increased strictly.")
class StationInfoItemKeyedDataSource(
    private var initialLoadState: NetworkState,
    private var loadMoreState: NetworkState
) : ItemKeyedDataSource<Int, StationInfoItem>(), KoinComponent {

    private val TAG = "StationInfoDataSource"
    private val traRepository: ITraRepository by inject()
    private val scope: CoroutineScope by inject()
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<StationInfoItem>
    ) {
        val initKey = params.requestedInitialKey ?: 0
        val size = params.requestedLoadSize

        Log.i(TAG, "loadInitial, initKey=$initKey, size=$size")

        scope.launch {
            initialLoadState = NetworkState.LOADING
            val response = traRepository.getStationInfo(initKey, size)
            initialLoadState = if (response.isSuccessful) {
                val item = response.body() as List<StationInfoItem>
                callback.onResult(item)
                NetworkState.IDLE
            } else {
                Log.e(TAG, "loadInitial failed, e=" + response.errorBody().toString())
                NetworkState.ERROR
            }
        }
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<StationInfoItem>
    ) {
        if (NetworkState.LOADING == loadMoreState) return

        val prevKey = params.key
        val size = params.requestedLoadSize

        Log.i(TAG, "loadAfter, prevKey=$prevKey, size=$size")

        val newKey = prevKey + size

        scope.launch {
            loadMoreState = NetworkState.LOADING
            val response = traRepository.getStationInfo(newKey, size)
            loadMoreState = if (response.isSuccessful) {
                val item = response.body() as List<StationInfoItem>
                callback.onResult(item)
                NetworkState.IDLE
            } else {
                Log.e(TAG, "loadAfter failed, e=" + response.errorBody().toString())
                NetworkState.ERROR
            }
        }
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<StationInfoItem>
    ) {
        // No need to implement
    }

    override fun getKey(item: StationInfoItem): Int {
        return 0
    }
}