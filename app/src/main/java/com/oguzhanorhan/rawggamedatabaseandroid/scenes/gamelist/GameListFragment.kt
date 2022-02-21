package com.oguzhanorhan.rawggamedatabaseandroid.scenes.gamelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.oguzhanorhan.rawggamedatabaseandroid.common.injectFeature
import com.oguzhanorhan.rawggamedatabaseandroid.common.onQueryTextChanged
import com.oguzhanorhan.rawggamedatabaseandroid.databinding.FragmentGameListBinding
import org.koin.android.ext.android.bind
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

        /* binding.itemList.adapter = ItemListAdapter(
            ItemListAdapter.OnClickListener {
                viewModel.displayItemDetails(it)
            }
        ) */
        binding.searchGameResultList.adapter = ItemListAdapter(
            ItemListAdapter.OnClickListener {
                viewModel.displayItemDetails(it)
            }
        )
        /* binding.searchBar.setOnQueryTextFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                binding.itemList.visibility = View.GONE
                binding.searchGameResultList.visibility = View.VISIBLE
            } else {
                binding.itemList.visibility = View.VISIBLE
                binding.searchGameResultList.visibility = View.GONE
            }
        } */
        /* binding.searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                //handleSearchBox(query)
                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        }) */
        binding.searchBar.onQueryTextChanged {
            viewModel.searchQuery.value = it
        }
        viewModel.items.observe(this, { gameList ->
            gameList?.let {
                if (gameList.size < 3) {
                    binding.imageSlider.adapter = ImageSliderAdapter(context, gameList.take(gameList.size).toList())
                } else {
                    binding.imageSlider.adapter = ImageSliderAdapter(context, gameList.take(3).toList())
                }
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