package com.example.data.repos

import com.example.data.api.KinopoiskApi
import com.example.data.db.dao.FavDao
import com.example.data.db.dao.FilmDao
import com.example.data.db.models.FavEntity.Companion.toFavEntity
import com.example.data.db.models.FilmEntity.Companion.toFilmEntity
import com.example.domain.models.FilmDescription
import com.example.domain.models.FilmItem
import com.example.domain.repo.IFilmsRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FilmsRepository @Inject constructor(
    private val api: KinopoiskApi,
    private val favDao: FavDao,
    private val filmDao: FilmDao,
) : IFilmsRepo {
    override suspend fun getFilms(fromCache: Boolean) =
        withContext(Dispatchers.IO) {
            if (fromCache) {
                filmDao.getAll().map {
                    it.toDomainModel(
                        isFav = favDao.findById(it.id) != null,
                    )
                }
            } else {
                val items = api.getPremieres(
                    year = 2023,
                    month = "December",
                    token = "72f48831-3a9a-457c-b24e-acbf7e2b9e90",
                ).items
                items.map {
                    it.toDomain(
                        isFav = favDao.findById(it.kinopoiskId) != null,
                    )
                }
            }
        }

    override suspend fun getFilmFromCache(id: Int) =
        withContext(Dispatchers.IO) {
            filmDao.getById(id)?.let {
                it.toDomainModel(
                    isFav = favDao.findById(it.id) != null,
                )
            }
        }

    override suspend fun saveToCache(items: List<FilmItem>) =
        withContext(Dispatchers.IO) {
            filmDao.getAll().forEach {
                if (favDao.findById(it.id) == null) filmDao.deleteById(it.id)
            }
            filmDao.insertAll(items.map { it.toFilmEntity() })
        }

    override suspend fun getDescriptionById(id: Int) =
        withContext(Dispatchers.IO) {
            val description = api.getDescriptionById(
                id = id,
                token = "72f48831-3a9a-457c-b24e-acbf7e2b9e90",
            )
            FilmDescription(
                id,
                description.description
            )
        }

    override suspend fun addToFav(filmItem: FilmItem) =
        withContext(Dispatchers.IO) {
            favDao.insert(filmItem.toFavEntity())
        }

    override suspend fun removeFromFav(filmItem: FilmItem) =
        withContext(Dispatchers.IO) {
            favDao.delete(filmItem.toFavEntity())
        }
}