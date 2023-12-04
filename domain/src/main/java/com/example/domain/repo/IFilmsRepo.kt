package com.example.domain.repo

import com.example.domain.models.FilmDescription
import com.example.domain.models.FilmItem

interface IFilmsRepo {
    suspend fun getFilms(fromCache: Boolean): List<FilmItem>

    suspend fun getDescriptionById(id: Int): FilmDescription

    suspend fun addToFav(filmItem: FilmItem)

    suspend fun removeFromFav(filmItem: FilmItem)

    suspend fun saveToCache(items: List<FilmItem>)

    suspend fun getFilmFromCache(id: Int): FilmItem?

    suspend fun getAllFavs(): List<FilmItem>
}