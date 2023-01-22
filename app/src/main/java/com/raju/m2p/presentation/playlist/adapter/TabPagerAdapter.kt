package com.raju.m2p.presentation.playlist.adapter

import android.os.Parcelable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class TabPagerAdapter(fm: FragmentManager?, private val tabs: List<Fragment>) :
    FragmentStatePagerAdapter(
        fm!!
    ) {
    override fun getItem(position: Int): Fragment {
        return tabs[position]
    }

    override fun getCount(): Int {
        return tabs.size
    }

    override fun saveState(): Parcelable? {
        return null
    }
}