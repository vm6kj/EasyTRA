package com.kun.easytra.ui.citychooser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.kun.easytra.R

class CityChooserFragment : Fragment() {

    companion object {
        fun newInstance() =
            CityChooserFragment()
    }

    private lateinit var viewModel: CityChooserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_city_chooser, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CityChooserViewModel::class.java)
        // TODO: Use the ViewModel
    }
}