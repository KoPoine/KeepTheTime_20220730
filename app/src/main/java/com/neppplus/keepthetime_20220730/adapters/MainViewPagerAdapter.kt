package com.neppplus.keepthetime_20220730.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.neppplus.keepthetime_20220730.fragments.AppointmentsFragment
import com.neppplus.keepthetime_20220730.fragments.InvitedFragment
import com.neppplus.keepthetime_20220730.fragments.SettingFragment

class MainViewPagerAdapter(fa : FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> AppointmentsFragment()
            1 -> InvitedFragment()
            else -> SettingFragment()
        }
    }
}