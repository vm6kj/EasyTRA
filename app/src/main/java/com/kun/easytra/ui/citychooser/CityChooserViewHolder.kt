package com.kun.easytra.ui.citychooser

import androidx.recyclerview.widget.RecyclerView
import com.kun.easytra.databinding.CityItemInCityChooserBinding
import org.koin.core.KoinComponent

class CityChooserViewHolder constructor(private val itemLayoutBinding: CityItemInCityChooserBinding) :
    RecyclerView.ViewHolder(itemLayoutBinding.root), KoinComponent {

    fun bindTo(viewModel: CityChooserViewModel, city: String) {
        itemLayoutBinding.viewmodel = viewModel
        itemLayoutBinding.cityName = city
        itemLayoutBinding.executePendingBindings()
    }
}