package com.example.storyapp_kotlin.Views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.storyapp_kotlin.NavigationHelper.NavigationHelper
import com.example.storyapp_kotlin.R
import com.example.storyapp_kotlin.databinding.FragmentProfilePageBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class profilePageFragment : Fragment() {

    private lateinit var binding: FragmentProfilePageBinding

    @Inject
    lateinit var navigationHelper: NavigationHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfilePageBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}