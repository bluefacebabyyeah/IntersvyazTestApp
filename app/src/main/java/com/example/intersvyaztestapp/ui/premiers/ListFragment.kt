package com.example.intersvyaztestapp.ui.premiers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.domain.models.FilmItem
import com.example.intersvyaztestapp.R
import com.example.intersvyaztestapp.databinding.FragmentListBinding
import com.example.intersvyaztestapp.toast
import com.example.intersvyaztestapp.ui.details.DetailsFragmentArgs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment(R.layout.fragment_list) {
    private lateinit var adapter: FilmItemAdapter
    private val viewModel by viewModels<ListViewModel>()

    private val binding by viewBinding(FragmentListBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        observeState()
        setView()
        viewModel.loadItems()
    }

    private fun setView() {
        binding.bSearch.setOnClickListener {
            viewModel.filterItems(binding.etSearch.text.toString().ifBlank { "" })
        }
    }

    private fun observeState() {
        viewModel.items.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        viewModel.error.observe(viewLifecycleOwner) {
            if (it != null) {
                requireContext().toast(it)
            }
        }
        viewModel.loading.observe(viewLifecycleOwner) {
            binding.pbLoading.isVisible = it
        }
        viewModel.modifiedIndex.observe(viewLifecycleOwner) {
            if (it != null) {
                adapter.notifyItemChanged(it)
            }
        }
    }

    private fun setAdapter() {
        adapter = FilmItemAdapter(
            onClick = { openDetailsPage(it) },
            onFavClick = { viewModel.switchFav(it) }
        )
        binding.rvElements.adapter = adapter
        binding.rvElements.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun openDetailsPage(filmItem: FilmItem) =
        findNavController().navigate(
            resId = R.id.action_listFragment_to_detailsFragment,
            args = DetailsFragmentArgs(filmItem.id).toBundle(),
        )
}