package com.raju.m2p.presentation.playlist

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.PagerAdapter
import com.google.android.material.tabs.TabLayout
import com.raju.m2p.bean.playlist.PlaylistData
import com.raju.m2p.bean.playlist.ResultBean
import com.raju.m2p.databinding.ActivityPlaylistBinding
import com.raju.m2p.presentation.base.BaseActivity
import com.raju.m2p.presentation.playlist.adapter.TabPagerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlaylistActivity : BaseActivity() {
    private lateinit var binding: ActivityPlaylistBinding
    private val sharedVM: SharedViewModel by viewModels()
    private val viewModel: PlaylistViewModel by viewModels()
    private lateinit var playlist: MutableList<ResultBean>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlaylistBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpTabs()
        viewModel.init()
        initObservers()
    }


    private fun setUpTabs() {
        binding.tlTabs.addTab(binding.tlTabs.newTab().setText("Grid"))
        binding.tlTabs.addTab(binding.tlTabs.newTab().setText("Linear"))

        val fragmentList: MutableList<Fragment> = ArrayList()
        fragmentList.add(PlaylistFragment())
        fragmentList.add(PlaylistFragment())

        val adapter: PagerAdapter = TabPagerAdapter(supportFragmentManager, fragmentList)

        binding.vpViewPager.adapter = adapter
        binding.vpViewPager.offscreenPageLimit = 2
        binding.vpViewPager.currentItem = 0
        binding.vpViewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(binding.tlTabs))
        binding.tlTabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                sendData(tab.position)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
                sendData(tab.position)
            }
        })

    }

    private fun initObservers() {
        viewModel.playlistLD.observe(this) {
            playlist = it as MutableList<ResultBean>
            sharedVM.sendPlaylistData(PlaylistData(playlist, 0))
            binding.iLoadedView.rlProgressContainer.visibility = View.GONE
        }
    }

    private fun sendData(position: Int) {
        binding.vpViewPager.currentItem = position
        sharedVM.sendPlaylistData(PlaylistData(playlist, binding.vpViewPager.currentItem))
    }

}