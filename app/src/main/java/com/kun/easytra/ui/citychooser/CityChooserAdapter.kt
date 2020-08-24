package com.kun.easytra.ui.citychooser

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kun.easytra.databinding.CityItemInCityChooserBinding

class CityChooserAdapter(private val cityChooserViewModel: CityChooserViewModel) :
    RecyclerView.Adapter<CityChooserViewHolder>() {

    private var allCity = emptyList<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityChooserViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CityItemInCityChooserBinding.inflate(layoutInflater, parent, false)
        return CityChooserViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return allCity?.size ?: 0
    }

    override fun onBindViewHolder(holder: CityChooserViewHolder, position: Int) {
        allCity?.get(position)?.let {
            holder.bindTo(cityChooserViewModel, it)
        }
    }

    internal fun updateCityList(allCity: List<String>) {
        this.allCity = allCity
        notifyDataSetChanged()
    }
}