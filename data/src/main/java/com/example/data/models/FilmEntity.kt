package com.example.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.models.FilmItem

@Entity
data class FilmEntity(
    val title: String,
    val image: String,

    val duration: Int,
    val year: Int,
    val genre: List<String>,
    val countries: List<String>,

    val isFavourite: Boolean,
    @PrimaryKey(autoGenerate = true) val id:Int = 0
) {
    fun toDomainModel() =
        FilmItem(
            id = id,
            title = title,
            image = image,
            duration = duration,
            year = year,
            genre = genre,
            countries = countries,
            isChecked = false
        )
}
