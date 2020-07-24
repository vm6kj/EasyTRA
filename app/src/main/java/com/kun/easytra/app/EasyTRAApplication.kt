package com.kun.easytra.app

import androidx.multidex.MultiDexApplication
import com.kun.easytra.di.fragmentModule
import com.kun.easytra.di.mainModule
import com.kun.easytra.di.networkModule
import com.kun.easytra.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin

class EasyTRAApplication : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@EasyTRAApplication)
            fragmentFactory()
            modules(viewModelModule, fragmentModule, networkModule, mainModule)
        }
    }
}