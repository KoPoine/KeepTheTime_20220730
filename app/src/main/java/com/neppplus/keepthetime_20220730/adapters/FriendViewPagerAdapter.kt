package com.neppplus.keepthetime_20220730.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.neppplus.keepthetime_20220730.fragments.InviteFriendsFragment
import com.neppplus.keepthetime_20220730.fragments.MyFriendsFragment

class FriendViewPagerAdapter(fm : FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> MyFriendsFragment()
            else -> InviteFriendsFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "내 친구 목록"
            else -> "친구 추가 요청"
        }
    }
}