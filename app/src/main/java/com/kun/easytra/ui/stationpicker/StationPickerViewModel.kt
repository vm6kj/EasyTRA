package com.kun.easytra.ui.stationpicker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.kun.easytra.tradata.repository.ITraRepository
import kotlinx.coroutines.Dispatchers
import org.koin.core.KoinComponent
import org.koin.core.inject

class StationPickerViewModel : ViewModel(), KoinComponent {

    private val traRepository: ITraRepository by inject()

    val stationInfo = liveData(Dispatchers.IO) {
        val response = traRepository.getStationInfo(null, null)
        if (response.isSuccessful) {
            emit(response.body())
        }
    }
}