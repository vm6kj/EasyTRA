package com.kun.easytra.ui.allstation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.kun.easytra.R
import kotlinx.android.synthetic.main.fragment_all_station.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.KoinComponent
import org.koin.core.inject

class AllStationFragment : Fragment(), KoinComponent {
    private val allStationViewModel: AllStationViewModel by viewModel()
    private val stationAdapter: AllStationAdapter by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_all_station, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        list_all_station.addItemDecoration(DividerItemDecoration(this.context, LinearLayoutManager.VERTICAL))
        list_all_station.adapter = stationAdapter

        allStationViewModel.getStationInfoList().observe(this.viewLifecycleOwner, Observer {
            stationAdapter.submitList(it)
        })
    }

}