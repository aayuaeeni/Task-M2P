package com.raju.m2p.presentation.playlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.raju.m2p.R
import com.raju.m2p.bean.playlist.ResultBean
import com.raju.m2p.databinding.PlaylistItemBinding
import com.raju.m2p.databinding.PlaylistItemGridBinding
import com.raju.m2p.presentation.utils.AppUtility
import com.raju.utils.loadImageFromUrl

class PlaylistAdapter(
    val onItemClick: ((data: ResultBean?) -> Unit)? = null,
    var playlists: List<ResultBean>,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val GRID_TYPE = 1
    private val LINEAR = 2
    private lateinit var bindingGrid: PlaylistItemGridBinding
    private lateinit var bindingLinear: PlaylistItemBinding


    inner class GridViewHolder(binding: PlaylistItemGridBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {

            binding.rlContainer.layoutParams.height =
                (AppUtility.getScreenWidth() - AppUtility.convertDpToPx(64f)) / 3
        }
    }

    inner class LinearViewHolder(binding: PlaylistItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == GRID_TYPE) {
            bindingGrid =
                PlaylistItemGridBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            GridViewHolder(bindingGrid)
        } else {
            bindingLinear =
                PlaylistItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            LinearViewHolder(bindingLinear)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (playlists[position].viewType == GRID_TYPE) {
            GRID_TYPE
        } else LINEAR
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val playlist = playlists[position]
        if (viewHolder.itemViewType == GRID_TYPE) {
            bindingGrid.ivImage.loadImageFromUrl(playlist.artworkUrl60, R.drawable.ic_logo)
            bindingGrid.tvTrackTitile.text = playlist.trackName
            bindingGrid.tvTrackName.text = playlist.artistName
            bindingGrid.root.setOnClickListener {
                onItemClick?.invoke(playlist)
            }

        } else if (viewHolder.itemViewType == LINEAR) {
            bindingLinear.ivImage.loadImageFromUrl(playlist.artworkUrl60, R.drawable.ic_logo)
            bindingLinear.tvTrackTitle.text = playlist.trackName
            bindingLinear.tvTrackName.text = playlist.artistName
            bindingLinear.root.setOnClickListener {
                onItemClick?.invoke(playlist)
            }
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int {
        if (playlists.isEmpty()) {
            return 0
        }
        return playlists.size
    }

    fun filterList(filteredPlaylists: List<ResultBean>) {
        this.playlists = filteredPlaylists
        notifyDataSetChanged()
    }
}