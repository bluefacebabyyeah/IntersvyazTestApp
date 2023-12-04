package com.example.domain.repo

import com.example.domain.models.FilmDescription
import com.example.domain.models.FilmItem

interface IFilmsRepo {
    suspend fun getFilms(): List<FilmItem>
    suspend fun getDescriptionById(id: Int): FilmDescription
}