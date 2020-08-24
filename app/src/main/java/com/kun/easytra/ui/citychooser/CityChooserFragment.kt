package com.kun.easytra.ui.citychooser

import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.kun.easytra.R
import com.kun.easytra.databinding.FragmentCityChooserBinding
import com.kun.easytra.ui.BaseBindingFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.core.parameter.parametersOf

class CityChooserFragment : BaseBindingFragment<FragmentCityChooserBinding>() {

    companion object {
        private const val TAG = "CityChooserFragment"
    }

    private val cityChooserViewModel by sharedViewModel<CityChooserViewModel>()
    private val cityChooserAdapter by inject<CityChooserAdapter> { parametersOf(cityChooserViewModel) }

    override fun getLayoutId(): Int {
        return R.layout.fragment_city_chooser
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        binding.viewmodel = cityChooserViewModel

        binding.listAllCity.addItemDecoration(
            DividerItemDecoration(
                this.context,
                DividerItemDecoration.HORIZONTAL
            )
        )
        binding.listAllCity.addItemDecoration(
            DividerItemDecoration(
                this.context,
                DividerItemDecoration.VERTICAL
            )
        )
        binding.listAllCity.layoutManager = GridLayoutManager(this.context, 3)
        binding.listAllCity.adapter = cityChooserAdapter

        cityChooserViewModel.allCity.observe(viewLifecycleOwner, Observer { allCity ->
            cityChooserAdapter.updateCityList(allCity)
        })

        cityChooserViewModel.cityClicked.observe(viewLifecycleOwner, Observer { event ->
            event.getContentIfNotHandled()?.let { city ->
                if (city.isEmpty()) {
                    Log.i(TAG, "city is empty")
                    return@Observer
                }
                Log.d(TAG, "onItemClick -> $city")
                activity?.findViewById<LinearLayout>(R.id.ll_city_chooser)?.let { it ->
                    Snackbar.make(it, city, Snackbar.LENGTH_SHORT).show()
                }
            }
        })
    }
}