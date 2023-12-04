package com.example.domain.usecases

import com.example.domain.repo.IFilmsRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetFilmsUseCase @Inject constructor(
    private val filmsRepo: IFilmsRepo
){
    suspend operator fun invoke(fromCache: Boolean) =
        withContext(Dispatchers.IO) {
            filmsRepo.getFilms(fromCache)
        }
}