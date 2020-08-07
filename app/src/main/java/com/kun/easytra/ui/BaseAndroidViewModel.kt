package com.kun.easytra.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import org.koin.core.KoinComponent

abstract class BaseAndroidViewModel(private val context: Application) : AndroidViewModel(context),
    KoinComponent