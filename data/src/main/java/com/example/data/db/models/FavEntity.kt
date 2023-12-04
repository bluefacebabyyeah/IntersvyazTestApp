package com.example.data.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.models.FilmItem

@Entity
data class FavEntity(
    @PrimaryKey val filmId: Int,
) {
    companion object {
        fun FilmItem.toFavEntity() =
            FavEntity(id)
    }
}
