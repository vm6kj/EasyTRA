package com.kun.easytra.ui.citychooser

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.city_item_in_city_chooser.view.*
import org.koin.core.Koin
import org.koin.core.KoinComponent
import org.koin.core.inject

class CityChooserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), KoinComponent {

    private val cityChooserViewModel: CityChooserViewModel by inject()

    fun bindTo(city: String, onItemClicked: (String) -> Unit) {
        itemView.txt_city.text = city
        itemView.txt_city.setOnClickListener {
            Log.d("KCTEST", city + " touched")
            onItemClicked.invoke(city + " touched")
        }
    }
}