package com.oguzhanorhan.rawggamedatabaseandroid.scenes.gamelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.oguzhanorhan.rawggamedatabaseandroid.common.injectFeature
import com.oguzhanorhan.rawggamedatabaseandroid.common.onQueryTextChanged
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

        binding.searchGameResultList.adapter = ItemListAdapter(
            ItemListAdapter.OnClickListener {
                viewModel.displayItemDetails(it)
            }
        )

        binding.searchBar.onQueryTextChanged {
            viewModel.searchQuery.value = it
        }
        viewModel.items.observe(this, { gameList ->
            val cl = ImageSliderAdapter.OnClickListener {
                viewModel.displayItemDetails(it)
            }
            gameList?.let {
                if (gameList.size < 3) {
                    binding.imageSlider.adapter = ImageSliderAdapter(context, gameList.take(gameList.size).toList(), cl)
                } else {
                    binding.imageSlider.adapter = ImageSliderAdapter(context, gameList.take(3).toList(),cl)
                }
                binding.indicator.setViewPager(binding.imageSlider)
            }
        })

        viewModel.navigateToSelectedItem.observe(
            this.viewLifecycleOwner,
            Observer { item ->
                item?.let {
                    Navigation.findNavController(binding.root).navigate(GameListFragmentDirections.actionGameListFragmentToGameDetailsFragment(it))
                    viewModel.displayItemDetailsComplete()
                }
            }
        )

        viewModel.searchGameResult.observe(viewLifecycleOwner) {
            val adapter = binding.searchGameResultList.adapter as ItemListAdapter
            adapter.submitList(it)
        }


        return binding.root
    }
}