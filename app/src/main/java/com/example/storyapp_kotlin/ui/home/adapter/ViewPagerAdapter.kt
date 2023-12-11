package com.example.storyapp_kotlin.ui.home.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.storyapp_kotlin.ui.home.HomePageFragment

class ViewPagerAdapter(activity : HomePageFragment, private val fragmentList : List<Fragment>) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> fragmentList[0]
            1 -> fragmentList[1]
            else -> fragmentList[0]
        }
    }
}