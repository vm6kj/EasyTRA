package com.kun.easytra.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import org.koin.core.KoinComponent

abstract class BaseAndroidViewModel(application: Application) : AndroidViewModel(application),
    KoinComponent