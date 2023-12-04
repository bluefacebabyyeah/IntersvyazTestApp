package com.example.data.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.models.FilmItem

@Entity
data class FilmEntity(
    val title: String,
    val image: String,
    val duration: Int,
    val year: Int,
    val genre: String,
    val countries: String,
    @PrimaryKey val id: Int,
) {
    companion object {
        private const val SPLITTER = ",_###_,"

        fun FilmItem.toFilmEntity() =
            FilmEntity(
                id = id,
                title = title,
                image = image,
                duration = duration,
                year = year,
                genre = genre.joinToString(SPLITTER),
                countries = countries.joinToString(SPLITTER),
            )
    }

    fun toDomainModel(isFav: Boolean) =
        FilmItem(
            id = id,
            title = title,
            image = image,
            duration = duration,
            year = year,
            genre = genre.split(SPLITTER),
            countries = countries.split(SPLITTER),
            isFavourite = isFav,
        )
}
