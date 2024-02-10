package com.example.storyapp_kotlin.ui.common_rv

import android.app.ActionBar.LayoutParams
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.example.storyapp_kotlin.databinding.CommonRvItemLayoutBinding
import com.example.storyapp_kotlin.databinding.CommonRvLayoutBinding
import com.example.storyapp_kotlin.databinding.FragmentTrendingBinding
import com.example.storyapp_kotlin.models.StoryModel
import com.example.storyapp_kotlin.models.UserModel

class commonRVadapter(val storyList: ArrayList<StoryModel>,val userList: ArrayList<UserModel>) : RecyclerView.Adapter<commonRVadapter.ViewHolder>() {
    class ViewHolder(val binding: CommonRvItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            CommonRvItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return storyList.takeIf { it.isNotEmpty() }?.size ?: 3
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        createItemUI(holder, position)
        matchCreatorToStory(holder,position)

        //for story book cover
        Glide.with(holder.itemView.context)
            .load(storyList[position].storyImageUrl)
            .override(448,644)
            .centerCrop()
            .into(holder.binding.bookCoverImageView)
    }

    private fun createItemUI(holder: ViewHolder, position: Int) {
        with(holder.binding) {
            storyTitleTextView.text = storyList[position].storyTitle
            //storyCreatorTextView.text = storyList[position].contributions?.get(0).toString()
            numberOfReaderTextView.text = storyList[position].numberOfReader.toString()
        }

    }

    fun matchCreatorToStory(holder: ViewHolder, position: Int){
        for (story in storyList){
            for (user in userList ){
                if (story.contributions?.get(0) == user.userId){
                    holder.binding.storyCreatorTextView.text = user.email
                }
            }
        }
    }
}
