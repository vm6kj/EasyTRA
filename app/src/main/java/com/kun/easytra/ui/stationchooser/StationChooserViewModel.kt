package com.kun.easytra.ui.stationchooser

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kun.easytra.Event

class StationChooserViewModel(application: Application) : AndroidViewModel(application) {

    private val _pickStationEvent = MutableLiveData<Event<Unit>>()
    val pickStationEvent: LiveData<Event<Unit>> = _pickStationEvent
    
}