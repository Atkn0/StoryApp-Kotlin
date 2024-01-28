package com.example.storyapp_kotlin.ui.createStory

import android.os.Bundle
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.TranslateAnimation
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.storyapp_kotlin.R
import com.example.storyapp_kotlin.databinding.FragmentCreateStoryBinding
import com.example.storyapp_kotlin.di.NavigationManager.NavigationManager
import com.example.storyapp_kotlin.models.SafeArgsObject
import com.example.storyapp_kotlin.models.StoryModel
import com.google.android.material.card.MaterialCardView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class createStoryFragment : Fragment() {

    private lateinit var binding: FragmentCreateStoryBinding
    private val createStoryViewModel : createStoryViewModel by viewModels()

    @Inject
    lateinit var navigationManager: NavigationManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            createStoryViewModel.checkUserCredit()
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateStoryBinding.inflate(inflater, container, false)


        createStoryViewModel.userCreditControl.observe(viewLifecycleOwner) { hasEnoughCredit ->
            println("userCreditObserver çalıştı $hasEnoughCredit")
            //Buradaki UI değişikliğini ayır!
            if (hasEnoughCredit) {
                binding.storyNextButton.isEnabled = true
                binding.storyNextButton.alpha = 1f
                binding.warningCardView.visibility = View.GONE
            }
            else {
                binding.storyNextButton.isEnabled = false
                binding.storyNextButton.alpha = 0.5f
                binding.warningCardView.visibility = View.VISIBLE
            }

        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.storyNextButton.setOnClickListener {
            storyNext()
        }

        wordCountListener()


    }


    private fun wordCountListener(){
        binding.editTextTextMultiLine.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: android.text.Editable?) {
                if (s!!.length > 0) binding.storyNextButton.isEnabled = true
                else binding.storyNextButton.isEnabled = false
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                createStoryViewModel.updateWordCount(s.toString())
            }

        })

        createStoryViewModel.wordCount.observe(viewLifecycleOwner) { wordCount ->
            binding.wordCountTextView.text = "$wordCount/50"
        }

        createStoryViewModel.isCreateButtonEnabled.observe(viewLifecycleOwner) { isEnabled ->
            println("isCreateButtonEnabled: $isEnabled")
            binding.storyNextButton.post {
                //Buradaki UI değişikliğini ayır!
                if (isEnabled) binding.storyNextButton.alpha = 1f
                else binding.storyNextButton.alpha = 0.5f
                binding.storyNextButton.isEnabled = isEnabled
            }
        }
    }

    private fun storyNext() {
        val storyTitle = binding.storyTitleEditText.text.toString()
        val storyContent = binding.editTextTextMultiLine.text.toString()

        val safe_object = SafeArgsObject(storyTitle,storyContent)


        lifecycleScope.launch {
            val action = createStoryFragmentDirections.actionCreateStoryFragmentToStoryOverviewFragment(safe_object)
            navigationManager.navigateTo(action)
        }
    }


}