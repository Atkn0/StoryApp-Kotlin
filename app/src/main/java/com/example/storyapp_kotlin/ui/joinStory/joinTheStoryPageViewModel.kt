package com.example.storyapp_kotlin.ui.joinStory

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.storyapp_kotlin.domain.usecase.GetCompleteStoryByIDUseCase
import com.example.storyapp_kotlin.domain.usecase.JoinTheCurrentStoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class joinTheStoryPageViewModel @Inject constructor(
    private val getCompleteStoryByIDUseCase: GetCompleteStoryByIDUseCase,
    private val joinTheCurrentStoryUseCase: JoinTheCurrentStoryUseCase
) : ViewModel() {

    var isJoinStorySuccess = MutableLiveData<Boolean>()

    fun joinTheStory(storyID: String, storyContent: String) {
        val isValid = controlTheEnteredStory(storyContent)

        if (isValid) {
            viewModelScope.launch {
                val currentStoryModel = getCompleteStoryByIDUseCase(storyID)
                currentStoryModel?.let { storyModel ->
                    val isSuccess = joinTheCurrentStoryUseCase(storyModel, storyContent)
                    isJoinStorySuccess.value = isSuccess
                }
            }
        } else {
            println("Story is not valid")
        }
    }


    fun controlTheEnteredStory(storyContent: String): Boolean {
        if (storyContent.isEmpty()) {
            println("Story is empty")
            return false
        }
        val wordCount = storyContent
            .replace("[.,;?!_*-]".toRegex(), "")
            .split("\\s+".toRegex())
            .size

        val isStoryValid = wordCount <= 50

        return isStoryValid
    }

}