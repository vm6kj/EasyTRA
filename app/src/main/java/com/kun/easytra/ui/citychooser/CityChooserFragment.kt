package com.kun.easytra.ui.citychooser

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.kun.easytra.R
import kotlinx.android.synthetic.main.fragment_city_chooser.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.KoinComponent
import org.koin.core.get
import org.koin.core.inject
import org.koin.core.parameter.parametersOf

class CityChooserFragment : Fragment(), KoinComponent {

    companion object {
        private const val TAG = "CityChooserFragment"
    }

    private val cityChooserViewModel: CityChooserViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_city_chooser, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        list_all_city.addItemDecoration(
            DividerItemDecoration(
                this.context,
                DividerItemDecoration.HORIZONTAL
            )
        )
        list_all_city.addItemDecoration(
            DividerItemDecoration(
                this.context,
                DividerItemDecoration.VERTICAL
            )
        )

        val onCityClicked: (city: String) -> Unit = {
            cityChooserViewModel.cityClicked(it)
        }
        val cityChooserAdapter: CityChooserAdapter = get{ parametersOf(onCityClicked) }
        list_all_city.layoutManager = GridLayoutManager(this.context, 3)
        list_all_city.adapter = cityChooserAdapter

        cityChooserViewModel.cityClicked.observe(this.viewLifecycleOwner, Observer { city ->
            if (city.isEmpty()) {
                return@Observer
            }
            Log.e("KCTEST", "onItemClick -> $city")
            this.view?.let { view ->
                Snackbar.make(view, city, Snackbar.LENGTH_SHORT).show() }
        })

        cityChooserViewModel.allCity.observe(this.viewLifecycleOwner, Observer {
            Log.d(TAG, "allCity changed!")
            cityChooserAdapter.notifyDataSetChanged()
        })
    }
}