package com.example.intersvyaztestapp.ui.details

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.domain.models.FilmItem
import com.example.domain.service.IReminderService
import com.example.intersvyaztestapp.R
import com.example.intersvyaztestapp.databinding.FragmentDetailBinding
import com.example.intersvyaztestapp.drawableFromId
import com.example.intersvyaztestapp.toast
import com.example.intersvyaztestapp.ui.MainActivity.Companion.permissionRequester
import com.example.intersvyaztestapp.ui.details.dialogs.SchedulePickerDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment(R.layout.fragment_detail) {
    private val binding by viewBinding(FragmentDetailBinding::bind)
    private val viewModel by viewModels<DetailsViewModel>()
    private val args by navArgs<DetailsFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setView()
        observeState()
        viewModel.loadData(args.id)
    }

    private fun setView() {
        binding.run {
            ivFav.setOnClickListener {
                viewModel.switchFav()
            }
            ivLoad.setOnClickListener {
                viewModel.downloadImage()
            }
            ivRemind.setOnClickListener {
                SchedulePickerDialog(requireContext()) {
                    viewModel.scheduleReminder(it)
                }.show()
            }
            ivShare.setOnClickListener {
                viewModel.share(requireActivity() as AppCompatActivity)
            }
        }
    }

    private fun observeState() {
        viewModel.error.observe(viewLifecycleOwner) {
            if (it != null) {
                requireContext().toast(it)
            }
        }
        viewModel.loading.observe(viewLifecycleOwner) {
            binding.pbLoading.isVisible = it
        }
        viewModel.film.observe(viewLifecycleOwner) {
            if (it != null) {
                displayFilmData(it)
            }
        }
        viewModel.desc.observe(viewLifecycleOwner) {
            setDesc(it)
        }
        viewModel.permissionsGranted.observe(viewLifecycleOwner) {
            if (it == false) {
                permissionRequester.requestStoragePermissions { granted ->
                    if (granted) viewModel.onPermissionsGranted()
                }
            }
        }
    }

    private fun setDesc(it: String?) {
        binding.tvDesc.text = it ?: "No description"
    }

    private fun displayFilmData(it: FilmItem) {
        binding.run {
            tvTitle.text = it.title
            tvGenres.text = it.genre.joinToString(", ").capitalize()
            ivFav.setImageDrawable(
                binding.root.context.drawableFromId(
                    if (it.isFavourite) R.drawable.baseline_favorite_24
                    else R.drawable.baseline_favorite_border_24,
                ),
            )
            Glide.with(binding.root)
                .load(it.image)
                .thumbnail(
                    Glide.with(binding.root)
                        .load(R.drawable.ph_movie),
                )
                .into(binding.ivPicture)
        }
    }
}