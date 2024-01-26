package com.example.storyapp_kotlin.ui.createStory

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.storyapp_kotlin.databinding.FragmentCreateStoryBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class createStoryFragment : Fragment() {

    private lateinit var binding: FragmentCreateStoryBinding
    private val createStoryViewModel : createStoryViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        createStoryViewModel.makeApiCallImage()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateStoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.createStorybutton.setOnClickListener {
            createStory()
        }

        binding.editTextTextMultiLine.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: android.text.Editable?) {
                if (s!!.length > 0) binding.createStorybutton.isEnabled = true
                else binding.createStorybutton.isEnabled = false
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    createStoryViewModel.updateWordCount(s.toString())
            }

        })

        wordCountListener()


    }


    private fun wordCountListener(){
        createStoryViewModel.wordCount.observe(viewLifecycleOwner) { wordCount ->
            binding.wordCountTextView.text = "$wordCount/50"
        }

        createStoryViewModel.isCreateButtonEnabled.observe(viewLifecycleOwner) { isEnabled ->
            println("isCreateButtonEnabled: $isEnabled")
            binding.createStorybutton.post {
                if (isEnabled) binding.createStorybutton.alpha = 1f
                else binding.createStorybutton.alpha = 0.5f
                binding.createStorybutton.isEnabled = isEnabled
            }
        }
    }

    private fun createStory() {
        val storyContent = binding.editTextTextMultiLine.text.toString()
        lifecycleScope.launch {
            createStoryViewModel.createStory(storyContent)
        }
    }


}