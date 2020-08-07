package com.kun.easytra.ui.stationchooser

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kun.easytra.Event
import com.kun.easytra.ui.BaseAndroidViewModel

class StationChooserViewModel(private val context: Application) : BaseAndroidViewModel(context) {

    private val _pickStationEvent = MutableLiveData<Event<Unit>>()
    val pickStationEvent: LiveData<Event<Unit>> = _pickStationEvent

}