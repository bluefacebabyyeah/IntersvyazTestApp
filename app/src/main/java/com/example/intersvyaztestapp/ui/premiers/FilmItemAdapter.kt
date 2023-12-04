package com.example.intersvyaztestapp.ui.premiers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.domain.models.FilmItem
import com.example.intersvyaztestapp.databinding.ItemFilmBinding

class FilmItemAdapter(
    val onClick: (FilmItem) -> Unit,
) : ListAdapter<FilmItem, FilmItemViewHolder>(object : DiffUtil.ItemCallback<FilmItem>(){
    override fun areItemsTheSame(oldItem: FilmItem, newItem: FilmItem): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: FilmItem, newItem: FilmItem): Boolean {
        return oldItem == newItem
    }

}) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmItemViewHolder {
        return FilmItemViewHolder(ItemFilmBinding
            .inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: FilmItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}