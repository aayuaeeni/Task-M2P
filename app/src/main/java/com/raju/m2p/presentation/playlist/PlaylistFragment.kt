package com.raju.m2p.presentation.playlist

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.raju.m2p.bean.playlist.ResultBean
import com.raju.m2p.databinding.FragmentPlaylistBinding
import com.raju.m2p.presentation.player.PlayerActivity
import com.raju.m2p.presentation.playlist.adapter.PlaylistAdapter
import com.raju.m2p.presentation.utils.AdaptiveSpacingItemDecoration
import com.raju.m2p.presentation.utils.AppUtility
import com.raju.utils.hideSoftKeyboard
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PlaylistFragment : Fragment() {
    private lateinit var binding: FragmentPlaylistBinding
    private val sharedVM: SharedViewModel by activityViewModels()
    private lateinit var decorator: AdaptiveSpacingItemDecoration

    //To instance because have to perform seperate search funtionality
    private var playlistAdapterGrid: PlaylistAdapter? = null
    private var playlistAdapterLinear: PlaylistAdapter? = null
    private var playlists: MutableList<ResultBean> = mutableListOf()
    private var searchTabPosition: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlaylistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        decorator = AdaptiveSpacingItemDecoration(AppUtility.convertDpToPx(16f), true)
        initObservers()
        initSearch()
    }

    private fun initSearch() {
        binding.etSearch.doAfterTextChanged {
            sharedVM.filteredPlaylist(it.toString())
        }
    }

    private fun initObservers() {
        sharedVM.playlistDataLD.observe(viewLifecycleOwner) {
            val tabPosition = it.tabPosition
            searchTabPosition = tabPosition
            setupRV(tabPosition)
            activity?.hideSoftKeyboard()
            binding.etSearch.setText("")
            if (tabPosition == 0) {
                for (list in it.result) {
                    list.viewType = 1
                }
            } else {
                for (list in it.result) {
                    list.viewType = 2
                }
            }
            val playlist = it.result
            playlists = playlist as MutableList<ResultBean>
            setupAdapter(playlist, tabPosition)
        }

        sharedVM.filteredPlaylist.observe(viewLifecycleOwner) {
            MainScope().launch {
                delay(70)
                if (searchTabPosition == 0) {
                    playlistAdapterGrid?.filterList(it)
                } else {
                    playlistAdapterLinear?.filterList(it)
                }
            }
        }
    }

    private fun setupRV(tabPosition: Int) {
        if (tabPosition == 0) {
            val gridManager = GridLayoutManager(requireActivity(), 3)
            binding.rvPlaylist.apply {
                layoutManager = gridManager
                setHasFixedSize(true)
                isNestedScrollingEnabled = true
            }
        } else {
            val linearManager =
                LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
            binding.rvPlaylist.apply {
                layoutManager = linearManager
                setHasFixedSize(true)
                isNestedScrollingEnabled = true
            }
        }
        binding.rvPlaylist.removeItemDecoration(decorator)
        binding.rvPlaylist.invalidateItemDecorations()
        binding.rvPlaylist.addItemDecoration(decorator)
    }

    private fun setupAdapter(it: List<ResultBean>?, tabPosition: Int) {
        if (tabPosition == 0) {
            it?.run {
                if (playlistAdapterGrid == null) {
                    playlistAdapterGrid = PlaylistAdapter(
                        onItemClick = { data: ResultBean? ->
                            data?.let { it1 -> openPlayer(it1) }
                        }, this
                    )
                    binding.rvPlaylist.adapter = playlistAdapterGrid
                } else {
                    playlistAdapterGrid?.filterList(this)
                }
            }
        } else {
            it?.run {
                if (playlistAdapterLinear == null) {
                    playlistAdapterLinear = PlaylistAdapter(
                        onItemClick = { data: ResultBean? ->
                            data?.let { it1 -> openPlayer(it1) }
                        }, this
                    )
                    binding.rvPlaylist.adapter = playlistAdapterLinear
                } else {
                    playlistAdapterLinear?.filterList(this)
                }
            }
        }
    }

    private fun openPlayer(data: ResultBean) {
        val intent = Intent(requireActivity(), PlayerActivity::class.java)
        intent.putExtra("data", data)
        startActivity(intent)
    }

}