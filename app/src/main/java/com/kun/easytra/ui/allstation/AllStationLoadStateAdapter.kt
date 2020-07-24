package com.kun.easytra.ui.allstation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kun.easytra.R
import kotlinx.android.synthetic.main.network_state_item.view.*
import org.koin.core.KoinComponent
import org.koin.core.inject

@Deprecated("Not in used. LoadStateAdapter should be used with PagingDataAdapter")
class AllStationLoadStateAdapter : LoadStateAdapter<AllStationLoadStateAdapter.NetworkStateItemViewHolder>(), KoinComponent {

    private val allStationAdapter: AllStationAdapter by inject()

    override fun onBindViewHolder(holder: NetworkStateItemViewHolder, loadState: LoadState) {
        holder.bindTo(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): NetworkStateItemViewHolder {
        return NetworkStateItemViewHolder(parent) {
            // tmp
        }
    }

    class NetworkStateItemViewHolder(
        parent: ViewGroup,
        private val retryCallback: () -> Unit
    ) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.network_state_item, parent, false)
    ) {
        fun bindTo(loadState: LoadState) {

            itemView.progress_bar.visibility = if (loadState is LoadState.Loading) {
                View.VISIBLE
            } else {
                View.INVISIBLE
            }

            itemView.retry_button.visibility = if (loadState is LoadState.Error) {
                View.VISIBLE
            } else {
                View.INVISIBLE
            }
            itemView.retry_button.setOnClickListener {
                retryCallback()
            }
            itemView.error_msg.visibility =
                if (!(loadState as? LoadState.Error)?.error?.message.isNullOrBlank()) {
                    View.VISIBLE
                } else {
                    View.INVISIBLE
                }
            itemView.error_msg.text = (loadState as? LoadState.Error)?.error?.message
        }
    }
}