package com.example.domain.usecases

import com.example.domain.repo.IFilmsRepo
import javax.inject.Inject

class GetAllFavsUseCase @Inject constructor(
    private val filmRepo: IFilmsRepo,
) {
    suspend operator fun invoke() =
        filmRepo.getAllFavs()
}