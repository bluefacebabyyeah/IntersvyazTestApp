package com.example.domain.usecases

import com.example.domain.models.FilmItem
import com.example.domain.repo.IFilmsRepo
import javax.inject.Inject

class SwitchFavUseCase @Inject constructor(
    private val filmsRepo: IFilmsRepo,
) {
    suspend operator fun invoke(item: FilmItem) =
        if (item.isFavourite) filmsRepo.removeFromFav(item)
        else filmsRepo.addToFav(item)
}