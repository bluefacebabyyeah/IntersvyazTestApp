package com.example.domain.usecases

import com.example.domain.models.FilmDescription
import com.example.domain.repo.IFilmsRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetDescriptionByIdUseCase @Inject constructor(
    private val filmsRepo: IFilmsRepo
) {
    suspend operator fun invoke(id: Int): FilmDescription{
        return withContext(Dispatchers.IO){
            filmsRepo.getDescriptionById(id)
        }
    }
}
