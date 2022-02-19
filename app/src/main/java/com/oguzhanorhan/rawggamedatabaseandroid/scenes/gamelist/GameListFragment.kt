package com.oguzhanorhan.rawggamedatabaseandroid.scenes.gamelist

import androidx.fragment.app.Fragment
import com.oguzhanorhan.rawggamedatabaseandroid.databinding.FragmentGameListBinding
import org.koin.android.ext.android.inject

class GameListFragment : Fragment() {

    private val viewModel by inject<GameListVM>()
    private lateinit var binding: FragmentGameListBinding
}