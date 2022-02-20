package com.oguzhanorhan.rawggamedatabaseandroid.scenes.gamelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.oguzhanorhan.rawggamedatabaseandroid.common.injectFeature
import com.oguzhanorhan.rawggamedatabaseandroid.databinding.FragmentGameListBinding
import org.koin.android.ext.android.inject

class GameListFragment : Fragment() {

    private val viewModel by inject<GameListVM>()
    private lateinit var binding: FragmentGameListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreate(savedInstanceState)

        binding = FragmentGameListBinding.inflate(inflater)

        injectFeature()

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        binding.itemList.adapter = ItemListAdapter(
            ItemListAdapter.OnClickListener {
                viewModel.displayItemDetails(it)
            }
        )

        viewModel.items.observe(this, { gameList ->
            gameList?.let {
                if (gameList.size < 3) {
                    binding.imageSlider.adapter = ImageSliderAdapter(context, gameList.take(gameList.size).toList())
                } else {
                    binding.imageSlider.adapter = ImageSliderAdapter(context, gameList.take(3).toList())
                }
            }
        })



        return binding.root
    }
}