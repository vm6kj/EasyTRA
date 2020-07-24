package com.kun.easytra.ui.stationchooser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.kun.easytra.R
import kotlinx.android.synthetic.main.fragment_station_chooser.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class StationChooserFragment : Fragment() {

    companion object {
        fun newInstance() = StationChooserFragment()
    }

    private val TAG = "StationChooserFragment"
    private val stationChooserViewModel: StationChooserViewModel by viewModel()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_station_chooser, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        btn_departure.setOnClickListener {
            val action1 = StationChooserFragmentDirections.actionStationChooserFragmentToStationPickerFragment()
            findNavController().navigate(action1)
        }

        btn_arrive.setOnClickListener {
            val action2 = StationChooserFragmentDirections.actionStationChooserFragmentToAllStationFragment()
            findNavController().navigate(action2)
        }
    }
}