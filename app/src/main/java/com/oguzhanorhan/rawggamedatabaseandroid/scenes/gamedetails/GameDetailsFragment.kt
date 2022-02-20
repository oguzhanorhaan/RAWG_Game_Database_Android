package com.oguzhanorhan.rawggamedatabaseandroid.scenes.gamedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.oguzhanorhan.rawggamedatabaseandroid.common.injectFeature
import com.oguzhanorhan.rawggamedatabaseandroid.databinding.FragmentGameDetailBinding
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class GameDetailsFragment: Fragment() {

    private lateinit var binding: FragmentGameDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreate(savedInstanceState)
        binding = FragmentGameDetailBinding.inflate(inflater)
        val item = GameDetailsFragmentArgs.fromBundle(requireArguments()).selectedItem
        val viewModel by inject<GameDetailsVM>(){ parametersOf(item) }

        injectFeature()

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        return binding.root
    }
}