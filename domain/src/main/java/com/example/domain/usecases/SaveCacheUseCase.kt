package com.example.domain.usecases

import com.example.domain.models.FilmItem
import com.example.domain.repo.IFilmsRepo
import javax.inject.Inject

class SaveCacheUseCase @Inject constructor(
    private val filmRepo: IFilmsRepo,
) {
    suspend operator fun invoke(items: List<FilmItem>) =
        filmRepo.saveToCache(items)
}