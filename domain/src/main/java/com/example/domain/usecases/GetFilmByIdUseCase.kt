package com.example.domain.usecases

import com.example.domain.repo.IFilmsRepo
import javax.inject.Inject

class GetFilmByIdUseCase @Inject constructor(
    private val filmRepo: IFilmsRepo,
) {
    suspend operator fun invoke(id: Int) =
        filmRepo.getFilmFromCache(id)
}