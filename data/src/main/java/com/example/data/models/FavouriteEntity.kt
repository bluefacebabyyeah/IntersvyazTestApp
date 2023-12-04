package com.example.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.models.FilmItem

@Entity
data class FavouriteEntity(
    val kinopoiskId : Int,
    @PrimaryKey(autoGenerate = true) val id:Int = 0
)