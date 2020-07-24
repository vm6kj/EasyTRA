package com.kun.easytra.ui.allstation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.kun.easytra.tradata.repository.ITraRepository
import com.kun.easytra.tradata.responsebody.StationInfo
import org.koin.core.KoinComponent
import org.koin.core.inject

class AllStationViewModel : ViewModel(), KoinComponent {

    private val traRepository: ITraRepository by inject()

    fun getStationInfoList(): LiveData<PagedList<StationInfo.StationInfoItem>> {
        return traRepository.getStationInfoList()
    }
}