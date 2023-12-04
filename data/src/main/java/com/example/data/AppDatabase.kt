package com.example.data

import androidx.room.Database
import androidx.room.PrimaryKey
import androidx.room.RoomDatabase
import com.example.data.models.FavouriteEntity
import com.example.data.models.FilmEntity
import com.example.domain.models.FilmItem

@Database(
    entities = [
        FilmEntity::class,
        FavouriteEntity::class
    ],
    version = 1
)
abstract class AppDatabase:RoomDatabase() {
    abstract fun getFilmsByIdDao(id: Int): FilmsDao
    abstract fun getFavourites(): FavouritesDao
}