package com.kun.easytra.ui.allstation

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.kun.easytra.tradata.responsebody.StationInfo
import kotlinx.android.synthetic.main.station_item_in_all_list.view.*

class StationInfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bindTo(item: StationInfo.StationInfoItem) {
        itemView.txt_station_name.text = item.stationName?.zh_tw
        itemView.txt_phone_number.text = item.stationPhone
        itemView.txt_station_address.text = item.stationAddress
    }
}