package com.kun.easytra.ui.allstation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.kun.easytra.R
import com.kun.easytra.tradata.responsebody.StationInfo

class AllStationAdapter : PagedListAdapter<StationInfo.StationInfoItem, StationInfoViewHolder>(
    DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<StationInfo.StationInfoItem>() {
            override fun areItemsTheSame(
                oldItem: StationInfo.StationInfoItem,
                newItem: StationInfo.StationInfoItem
            ): Boolean {
                // Meaningless? Because the id is non-sequence
                return oldItem.stationID == newItem.stationID
            }

            override fun areContentsTheSame(
                oldItem: StationInfo.StationInfoItem,
                newItem: StationInfo.StationInfoItem
            ): Boolean {
                // Meaningless? Because the id is non-sequence
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StationInfoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.station_item_in_all_list, parent, false)
        return StationInfoViewHolder(view)
    }

    override fun onBindViewHolder(holder: StationInfoViewHolder, position: Int) {
        getItem(position)?.let { holder.bindTo(it) }
    }
}