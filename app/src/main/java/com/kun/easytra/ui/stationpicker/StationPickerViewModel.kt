package com.kun.easytra.ui.stationpicker

import android.R.id
import android.app.Application
import android.content.res.Resources
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.kun.easytra.db.CityStations
import com.kun.easytra.tradata.repository.ITraRepository
import com.kun.easytra.tradata.responsebody.StationInfoItem
import com.kun.easytra.ui.BaseAndroidViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.inject
import java.util.*
import kotlin.collections.ArrayList


class StationPickerViewModel(private val context: Application) : BaseAndroidViewModel(context) {

    private val traRepository: ITraRepository by inject()

    val cityStationsFromDb: MutableLiveData<CityStations> = MutableLiveData()

    init {
        getStationInfoListFromDb()
    }

    private fun getStationInfoListFromDb() {
        viewModelScope.launch(Dispatchers.IO) {
            cityStationsFromDb.postValue(traRepository.getCityStationInfoFromDb("臺中"))
        }
    }

    // TODO delete
    val stationInfo = liveData(Dispatchers.IO) {
        val response = traRepository.getStationInfo(null, null)
        if (response.isSuccessful) {
            val stationInfoList = response.body() as ArrayList<StationInfoItem>
            emit(stationInfoList)

            val cityStationsListToBeSaved: ArrayList<CityStations> = arrayListOf()

            val res: Resources = context.resources
            val config = res.configuration
            val targetLocale = config.locale
            config.locale = Locale.TRADITIONAL_CHINESE
            val twContext = context.createConfigurationContext(config)
            // Get cities in Chinese
            val cities = traRepository.getAllCities(twContext)

            cities.forEach {city ->
                val stationInfo = stationInfoList.filter { stationInfoItem ->
                    stationInfoItem.stationAddress?.contains(city)!!
                }

                cityStationsListToBeSaved.add(CityStations(city, stationInfo))
            }
            traRepository.insertCityStationInfoToDb(cityStationsListToBeSaved)
        }
    }
}