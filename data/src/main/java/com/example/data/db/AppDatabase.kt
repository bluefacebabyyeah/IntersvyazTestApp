package com.example.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.db.dao.FavDao
import com.example.data.db.dao.FilmDao
import com.example.data.db.models.FavEntity
import com.example.data.db.models.FilmEntity

@Database(
    entities = [
        FilmEntity::class,
        FavEntity::class,
    ],
    version = 1,
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getFilmDao(): FilmDao
    abstract fun getFavDao(): FavDao
}