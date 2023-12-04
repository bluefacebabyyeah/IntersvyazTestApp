package com.example.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.db.models.FavEntity

@Dao
interface FavDao {
    @Query("select * from faventity where filmId = :id")
    suspend fun findById(id: Int): FavEntity?

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(entity: FavEntity)

    @Delete
    suspend fun delete(entity: FavEntity)
}