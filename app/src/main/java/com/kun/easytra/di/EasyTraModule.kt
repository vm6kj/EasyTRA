package com.kun.easytra.di

import androidx.room.Room
import com.google.gson.Gson
import com.kun.easytra.db.TraDataBase
import com.kun.easytra.tradata.TraDataClient
import com.kun.easytra.tradata.repository.ITraRepository
import com.kun.easytra.tradata.repository.TraRepository
import com.kun.easytra.ui.allstation.AllStationAdapter
import com.kun.easytra.ui.allstation.AllStationFragment
import com.kun.easytra.ui.allstation.AllStationViewModel
import com.kun.easytra.ui.allstation.paging.StationDataSourceFactory
import com.kun.easytra.ui.citychooser.CityChooserAdapter
import com.kun.easytra.ui.citychooser.CityChooserFragment
import com.kun.easytra.ui.citychooser.CityChooserViewModel
import com.kun.easytra.ui.stationchooser.StationChooserFragment
import com.kun.easytra.ui.stationchooser.StationChooserViewModel
import com.kun.easytra.ui.stationpicker.StationPickerFragment
import com.kun.easytra.ui.stationpicker.StationPickerViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.fragment.dsl.fragment
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { StationChooserViewModel(androidApplication()) }
    viewModel { StationPickerViewModel(androidApplication()) }
    viewModel { AllStationViewModel() }
    viewModel { CityChooserViewModel(androidApplication()) }
}

val fragmentModule = module {
    fragment { StationChooserFragment() }
    fragment { StationPickerFragment() }
    fragment { AllStationFragment() }
    fragment { CityChooserFragment() }
}

val networkModule = module {
    single { TraDataClient.getApiClient() }
}

val dbModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            TraDataBase::class.java,
            TraDataBase::class.java.simpleName
        )
            // Not recommended in production app. Because it would recreate the tables if the
            // migration that would migrate old database schemas to the latest schema version are
            // not found.
            // https://developer.android.com/reference/androidx/room/RoomDatabase.Builder#fallbackToDestructiveMigration()
            .fallbackToDestructiveMigration()
            .build()
    }
}

val daoModule = module {
    single { get<TraDataBase>().stationInfoDao() }
}

val mainModule = module {
    single<ITraRepository> { TraRepository() }

    single { StationDataSourceFactory() }
    single { AllStationAdapter() }
//    single { (onCityClicked: (String) -> Unit) -> CityChooserAdapter(onCityClicked) }
    single { (cityChooserViewModel: CityChooserViewModel) ->
        CityChooserAdapter(
            cityChooserViewModel
        )
    }
    single { CoroutineScope(Dispatchers.IO) }
}

val gsonModule = module {
    single { Gson() }
}
//val functionModule = module {
//    single {  }
//}