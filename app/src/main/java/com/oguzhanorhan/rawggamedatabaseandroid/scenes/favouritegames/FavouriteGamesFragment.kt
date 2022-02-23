package com.oguzhanorhan.rawggamedatabaseandroid.scenes.favouritegames

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.oguzhanorhan.rawggamedatabaseandroid.common.injectFeature
import com.oguzhanorhan.rawggamedatabaseandroid.common.log.AppEventType
import com.oguzhanorhan.rawggamedatabaseandroid.databinding.FragmentFavouriteGamesBinding
import com.oguzhanorhan.rawggamedatabaseandroid.scenes.gamelist.ItemListAdapter
import org.koin.android.ext.android.inject

class FavouriteGamesFragment : Fragment() {

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
                viewModel.displayItemDetails(it)
            }
        )

        viewModel.navigateToSelectedItem.observe(
            this.viewLifecycleOwner,
            Observer { item ->
                item?.let {
                    AppEventType.ItemClicked(it.id.toString()).send()
                    Navigation.findNavController(binding.root).navigate(FavouriteGamesFragmentDirections.actionFavouriteGamesFragmentToGameDetailsFragment(it))
                    viewModel.displayItemDetailsComplete()
                }
            }
        )

        return binding.root
    }
}
