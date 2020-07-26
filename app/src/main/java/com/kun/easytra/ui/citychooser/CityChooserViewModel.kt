package com.kun.easytra.ui.citychooser

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kun.easytra.tradata.repository.ITraRepository
import org.koin.core.KoinComponent
import org.koin.core.inject

class CityChooserViewModel(private val context: Application) : AndroidViewModel(context), KoinComponent {

    private val traRepository: ITraRepository by inject()

    val allCity: MutableLiveData<List<String>> = MutableLiveData(emptyList())
    val cityClicked : MutableLiveData<String> = MutableLiveData("")

    init {
        getAllCity()
    }

    private fun getAllCity() {
        allCity.value = traRepository.getAllCities(context)
    }

    fun cityClicked(city: String) {
        cityClicked.value = city
    }
}