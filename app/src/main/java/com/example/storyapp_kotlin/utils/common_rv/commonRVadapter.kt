package com.example.storyapp_kotlin.utils.common_rv

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
import com.squareup.picasso.Picasso

class commonRVadapter(
    val storyList: ArrayList<StoryModel>,
    val userList: ArrayList<UserModel>,
    private val height : Int = 644,
    private val width : Int = 448
    ) : RecyclerView.Adapter<commonRVadapter.ViewHolder>() {
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
        matchCreatorToStory(holder, storyList[position])


        //for story book cover
        Glide.with(holder.itemView.context)
            .load(storyList[position].storyImageUrl)
            .override(width,height)
            .centerCrop()
            .into(holder.binding.bookCoverImageView)


    }

    private fun createItemUI(holder: ViewHolder, position: Int) {
        with(holder.binding) {
            storyTitleTextView.text = storyList[position].storyTitle
            numberOfReaderTextView.text = storyList[position].numberOfReader.toString()
        }

    }

    private fun matchCreatorToStory(holder: ViewHolder, currentStory: StoryModel) {
        currentStory.contributions?.get(0)?.let { creatorId ->
            val matchingUser = userList.find { it.userId == creatorId }
            holder.binding.storyCreatorTextView.text = matchingUser?.email ?: "Unknown Creator"
        }
    }

    fun updateData(newStoryList : ArrayList<StoryModel>,newUserList : ArrayList<UserModel>){
        storyList.clear()
        storyList.addAll(newStoryList)
        userList.clear()
        userList.addAll(newUserList)
        notifyDataSetChanged()
    }
}
