package com.example.intersvyaztestapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.intersvyaztestapp.R
import com.example.intersvyaztestapp.databinding.FragmentListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment(R.layout.fragment_list) {

    private lateinit var adapter: FilmItemAdapter
    private val viewModel by viewModels<ListViewModel>()

    private val binding by viewBinding(FragmentListBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = FilmItemAdapter {
            viewModel.onItemClick(it)
        }
        binding.rvElements.adapter = adapter
        binding.rvElements.layoutManager = LinearLayoutManager(requireContext())

        viewModel.items.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }

        viewModel.modifiedIndex.observe(viewLifecycleOwner){
            if (it != null) {
                adapter.notifyItemChanged(it)
            }
        }

        viewModel.loadItems()
    }
}