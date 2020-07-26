package com.kun.easytra.ui.citychooser

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kun.easytra.R
import org.koin.core.KoinComponent
import org.koin.core.inject

class CityChooserAdapter(private val onCityCLicked: (city: String) -> Unit) : RecyclerView.Adapter<CityChooserViewHolder>(), KoinComponent {

    private val cityChooserViewModel: CityChooserViewModel by inject()
    private var allCity = cityChooserViewModel.allCity.value
    var onItemClick: ((String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityChooserViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.city_item_in_city_chooser, parent, false)
        return CityChooserViewHolder(view)
    }

    override fun getItemCount(): Int {
        return allCity?.size ?: 0
    }

    override fun onBindViewHolder(holder: CityChooserViewHolder, position: Int) {
        allCity?.get(position)?.let {
            holder.bindTo(it, onCityCLicked)
        }
    }
}