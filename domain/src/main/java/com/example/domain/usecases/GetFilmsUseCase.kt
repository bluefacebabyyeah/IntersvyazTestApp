package com.example.domain.usecases

import com.example.domain.models.FilmItem
import com.example.domain.repo.IFilmsRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetFilmsUseCase @Inject constructor(
    private val filmsRepo: IFilmsRepo
){
    suspend operator fun invoke(): List<FilmItem>{
        return withContext(Dispatchers.IO){
            filmsRepo.getFilms()
        }
    }
}