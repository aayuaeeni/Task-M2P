package com.raju.m2p.presentation.playlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.raju.m2p.bean.playlist.PlaylistData
import com.raju.m2p.bean.playlist.ResultBean
import java.util.*

class SharedViewModel : ViewModel() {
    val playlistDataLD = MutableLiveData<PlaylistData>()
    val filteredPlaylist = MutableLiveData<List<ResultBean>>()
    private var playlists: List<ResultBean> = mutableListOf()

    fun sendPlaylistData(playlistData: PlaylistData) {
        playlists = playlistData.result
        playlistDataLD.value = playlistData
    }

    fun filteredPlaylist(text: String) {
        val filteredlist: MutableList<ResultBean> = mutableListOf()
        if (text.isNotEmpty()) {
            for (item in playlists) {
                if (item.artistName.lowercase(Locale.getDefault()).contains(text.toLowerCase()) ||
                    item.trackName.lowercase(Locale.getDefault()).contains(text.toLowerCase())
                ) {
                    filteredlist.add(item)
                }
            }
            filteredPlaylist.value = filteredlist
        } else {
            filteredPlaylist.value = playlists
        }
    }
}