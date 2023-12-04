package com.example.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.data.db.models.FilmEntity

@Dao
interface FilmDao {
    @Query("select * from FilmEntity")
    suspend fun getAll(): List<FilmEntity>

    @Query("delete from FilmEntity where 1 = 1")
    suspend fun clearAll()

    @Query("select * from FilmEntity where id = :id")
    suspend fun getById(id: Int): FilmEntity?

    @Insert
    suspend fun insertAll(entities: List<FilmEntity>)
}