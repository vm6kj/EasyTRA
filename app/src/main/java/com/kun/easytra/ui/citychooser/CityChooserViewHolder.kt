package com.kun.easytra.ui.citychooser

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.city_item_in_city_chooser.view.*

class CityChooserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindTo(city: String, onItemClick: ((String) -> Unit)?) {
        itemView.txt_city.text = city
        itemView.setOnClickListener {
            onItemClick?.invoke(city)
        }
    }
}