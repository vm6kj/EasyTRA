package com.kun.easytra.ui.citychooser

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kun.easytra.R

class CityChooserAdapter : RecyclerView.Adapter<CityChooserViewHolder>() {

    private var allCity: Array<String>? = null
    var onItemClick: ((String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityChooserViewHolder {
        allCity = parent.context.resources.getStringArray(R.array.city_array)
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.city_item_in_city_chooser, parent, false)
        return CityChooserViewHolder(view)
    }

    override fun getItemCount(): Int {
        return allCity?.size ?: -1
    }

    override fun onBindViewHolder(holder: CityChooserViewHolder, position: Int) {
        allCity?.get(position)?.let {
            holder.bindTo(it, onItemClick)
        }
    }
}