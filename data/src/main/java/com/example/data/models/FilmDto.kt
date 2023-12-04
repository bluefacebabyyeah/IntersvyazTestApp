package com.example.data.models

import com.example.domain.models.FilmItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

data class FilmDto(
    val countries: List<Country>,
    val duration: Int,
    val genres: List<Genre>,
    val kinopoiskId: Int,
    val nameEn: String,
    val nameRu: String,
    val posterUrl: String,
    val posterUrlPreview: String,
    val premiereRu: String,
    val year: Int
) {
    data class Genre(
        val genre: String
    )

    data class Country(
        val country: String
    )

    fun toDomain(isFav: Boolean): FilmItem {
        return FilmItem(
            id = kinopoiskId,
            title = nameRu,
            image = posterUrl,
            duration = duration,
            year = year,
            genre = genres.map{ it.genre },
            countries = countries.map { it.country },
            isFavourite = isFav,
        )
    }
}