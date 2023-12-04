package com.example.domain.models

data class FilmItem(
    val id: Int,
    val title: String,
    val image: String,

    val duration: Int,
    val year: Int,
    val genre: List<String>,
    val countries: List<String>,

    val isChecked: Boolean
)