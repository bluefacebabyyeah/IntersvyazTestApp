package com.example.intersvyaztestapp.ui.favs

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.domain.models.FilmItem
import com.example.intersvyaztestapp.R
import com.example.intersvyaztestapp.databinding.FragmentFavouriteBinding
import com.example.intersvyaztestapp.ui.details.DetailsFragmentArgs
import com.example.intersvyaztestapp.ui.premiers.FilmItemAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouriteFragment : Fragment(R.layout.fragment_favourite) {
    private val binding by viewBinding(FragmentFavouriteBinding::bind)
    private val viewModel by viewModels<FavViewModel>()
    private lateinit var adapter: FilmItemAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        observeState()
        viewModel.loadItems()
    }

    private fun observeState() {
        viewModel.run {
            lastModifiedIndex.observe(viewLifecycleOwner) {
                if (it != null) adapter.notifyItemChanged(it)
            }
            films.observe(viewLifecycleOwner) {
                adapter.submitList(it)
                binding.tvEmpty.isVisible = it.isEmpty()
            }
            loading.observe(viewLifecycleOwner) {
                binding.pbLoading.isVisible = it
            }
        }
    }

    private fun setAdapter() {
        adapter = FilmItemAdapter(
            onClick = { openDetailsPage(it) },
            onFavClick = { viewModel.onSwitchFav(it) }
        )
        binding.rv.layoutManager = LinearLayoutManager(requireContext())
        binding.rv.adapter = adapter
    }

    private fun openDetailsPage(filmItem: FilmItem) =
        findNavController().navigate(
            resId = R.id.action_favouriteFragment_to_detailsFragment2,
            args = DetailsFragmentArgs(filmItem.id).toBundle(),
        )
}