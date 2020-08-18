package com.kun.easytra.app

import androidx.multidex.MultiDexApplication
import com.facebook.stetho.Stetho
import com.kun.easytra.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin

class EasyTRAApplication : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@EasyTRAApplication)
            fragmentFactory()
            modules(viewModelModule, fragmentModule, networkModule, dbModule, daoModule, mainModule, gsonModule)
        }
    }
}