package com.kun.easytra.ui.stationpicker

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.kun.easytra.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class StationPickerFragment : Fragment() {

    companion object {
        fun newInstance() = StationPickerFragment()
    }

    private val stationPickerViewModel: StationPickerViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_station_picker, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)



        stationPickerViewModel.stationInfo.observe(this.viewLifecycleOwner, Observer {
            Log.e("KCTEST", "Station size=" + it?.size)
            it?.forEach { stationInfoItem ->
                Log.e("KCTEST", "Station name=" + stationInfoItem.stationName)
            }
        })

    }
}