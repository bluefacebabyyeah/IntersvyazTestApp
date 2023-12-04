package com.example.intersvyaztestapp.ui.premiers

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.domain.models.FilmItem
import com.example.intersvyaztestapp.R
import com.example.intersvyaztestapp.databinding.ItemFilmBinding
import com.example.intersvyaztestapp.drawableFromId

class FilmItemViewHolder(
    private val binding: ItemFilmBinding,
) : ViewHolder(binding.root){

    @SuppressLint("SetTextI18n")
    fun bind(item: FilmItem) {
        binding.run{
            tvTitle.text = item.title
            tvYearCountry.text = "${item.year}" + item.countries.firstOrNull().let {
                if (it == null) ""
                else ", $it"
            }
            tvGenres.text = item.genre.joinToString(", ").capitalize()
            ivFav.setImageDrawable(
                binding.root.context.drawableFromId(
                    if (item.isFavourite) R.drawable.baseline_favorite_24
                    else R.drawable.baseline_favorite_border_24,
                ),
            )
            Glide.with(itemView)
                .load(item.image)
                .thumbnail(
                    Glide.with(itemView)
                        .load(R.drawable.ph_movie),
                )
                .into(binding.ivPicture)
            root.setOnClickListener{
                (bindingAdapter as FilmItemAdapter).onClick(item)
            }
            ivFav.setOnClickListener {
                (bindingAdapter as FilmItemAdapter).onFavClick(item)
            }
        }
    }
}