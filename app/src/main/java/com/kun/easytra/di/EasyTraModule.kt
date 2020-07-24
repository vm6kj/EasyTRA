package com.kun.easytra.di

import com.kun.easytra.tradata.TraDataClient
import com.kun.easytra.tradata.repository.ITraRepository
import com.kun.easytra.tradata.repository.TraRepository
import com.kun.easytra.ui.allstation.AllStationAdapter
import com.kun.easytra.ui.allstation.AllStationViewModel
import com.kun.easytra.ui.allstation.paging.StationDataSourceFactory
import com.kun.easytra.ui.stationchooser.StationChooserFragment
import com.kun.easytra.ui.stationchooser.StationChooserViewModel
import com.kun.easytra.ui.stationpicker.StationPickerFragment
import com.kun.easytra.ui.stationpicker.StationPickerViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.fragment.dsl.fragment
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { StationChooserViewModel(get()) }
    viewModel { StationPickerViewModel() }
}

val fragmentModule = module {
    fragment { StationChooserFragment() }
    fragment { StationPickerFragment() }
}

val networkModule = module {
    single { TraDataClient.getApiClient() }
}

val mainModule = module {
    single<ITraRepository> { TraRepository() }

    single { StationDataSourceFactory() }
    single { AllStationAdapter() }
    single { AllStationViewModel() }
    single { CoroutineScope(Dispatchers.IO) }
}

//val functionModule = module {
//    single {  }
//}