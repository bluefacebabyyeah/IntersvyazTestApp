package com.example.data.repos

import com.example.data.api.KinopoiskApi
import com.example.domain.models.FilmDescription
import com.example.domain.models.FilmItem
import com.example.domain.repo.IFilmsRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FilmsRepository @Inject constructor(
    private val api: KinopoiskApi,
) : IFilmsRepo {

    override suspend fun getFilms(): List<FilmItem> {
        return withContext(Dispatchers.IO){
            val items = api.getPremieres(2023, "December", "72f48831-3a9a-457c-b24e-acbf7e2b9e90").items
            items.map{
                it.toDomain()
            }.take(5)
        }
    }

    override suspend fun getDescriptionById(id: Int): FilmDescription{
        return withContext(Dispatchers.IO){
            val description = api.getDescriptionById(id)
            FilmDescription(
                id,
                description.description
            )
        }
    }
}