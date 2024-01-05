package com.example.storyapp_kotlin.ui.read_the_story.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.storyapp_kotlin.models.StoryModel
import com.example.storyapp_kotlin.ui.read_the_story.storyContentFragment

class readViewPagerAdapter(fm: FragmentManager,lifecycle: Lifecycle, val currentStoryModel : StoryModel) : FragmentStateAdapter(fm,lifecycle) {

    override fun getItemCount(): Int {
        return currentStoryModel.contributions!!.size
    }

    override fun createFragment(position: Int): Fragment {

        val currentUserID = currentStoryModel.contributions!![position]
        val currentStoryContent = currentStoryModel.storyContent!![currentUserID]

        return when(position){
            0 -> {
                storyContentFragment(currentStoryContent!!)
            }
            1 -> {
                storyContentFragment(currentStoryContent!!)
            }
            else -> {
                storyContentFragment("boş story content")

            }
        }
    }
}
