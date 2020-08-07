package com.kun.easytra.ui.citychooser

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kun.easytra.Event
import com.kun.easytra.tradata.repository.ITraRepository
import com.kun.easytra.ui.BaseAndroidViewModel
import kotlinx.coroutines.launch
import org.koin.core.inject

class CityChooserViewModel(private val context: Application) : BaseAndroidViewModel(context) {

    private val TAG = "CityChooserViewModel"
    private val traRepository by inject<ITraRepository>()

    var allCity: MutableLiveData<List<String>> = MutableLiveData(emptyList())
    var cityClicked: MutableLiveData<Event<String>> = MutableLiveData()

    init {
        getAllCity()
    }

    private fun getAllCity() {
        allCity.value = traRepository.getAllCities(context)
    }

    fun onCityClicked(city: String) = viewModelScope.launch {
        Log.d(
            TAG,
            "cityClicked.hasObservers()=" + cityClicked.hasObservers() + ", onCityClicked! city=$city"
        )
        cityClicked.value = Event(city)
    }
}