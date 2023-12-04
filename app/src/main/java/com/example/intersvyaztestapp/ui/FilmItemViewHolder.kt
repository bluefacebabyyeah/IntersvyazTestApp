package com.example.intersvyaztestapp.ui

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.domain.models.FilmItem
import com.example.intersvyaztestapp.R
import com.example.intersvyaztestapp.databinding.ItemFilmBinding

class FilmItemViewHolder(
    private val binding: ItemFilmBinding,
) : ViewHolder(binding.root){

    fun bind(item: FilmItem) {
        binding.run{
            tvTitle.text = item.title
            tvShortDescription.text = item.duration.toString()
            ivFav.setImageDrawable(
                if (item.isChecked == true)
                    (binding.root.context.getDrawable(R.drawable.baseline_favorite_24))
                else
                    binding.root.context.getDrawable(R.drawable.baseline_favorite_border_24))

            root.setOnClickListener{
                (bindingAdapter as FilmItemAdapter).onClick(item)
            }
        }
    }
}