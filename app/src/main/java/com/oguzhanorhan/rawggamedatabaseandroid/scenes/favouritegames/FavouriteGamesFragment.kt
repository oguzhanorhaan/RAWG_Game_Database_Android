package com.oguzhanorhan.rawggamedatabaseandroid.scenes.favouritegames

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.oguzhanorhan.rawggamedatabaseandroid.common.injectFeature
import com.oguzhanorhan.rawggamedatabaseandroid.databinding.FragmentFavouriteGamesBinding
import com.oguzhanorhan.rawggamedatabaseandroid.scenes.gamelist.ItemListAdapter
import org.koin.android.ext.android.inject

class FavouriteGamesFragment: Fragment() {

    private lateinit var binding: FragmentFavouriteGamesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreate(savedInstanceState)
        binding = FragmentFavouriteGamesBinding.inflate(inflater)

        val viewModel by inject<FavouriteGamesVM>()

        injectFeature()

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        binding.favouriteGameResultList.adapter = ItemListAdapter(
            ItemListAdapter.OnClickListener {
                // viewModel.displayItemDetails(it)
            }
        )

        return binding.root
    }
}