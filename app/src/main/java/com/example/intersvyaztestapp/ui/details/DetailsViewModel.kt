package com.example.intersvyaztestapp.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.FilmItem
import com.example.domain.usecases.GetDescriptionByIdUseCase
import com.example.domain.usecases.GetFilmByIdUseCase
import com.example.domain.usecases.SwitchFavUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getFilmByIdUseCase: GetFilmByIdUseCase,
    private val getDescriptionByIdUseCase: GetDescriptionByIdUseCase,
    private val switchFavUseCase: SwitchFavUseCase,
): ViewModel() {
    val desc = MutableLiveData<String?>()
    val film = MutableLiveData<FilmItem?>()
    val error = MutableLiveData<String?>()
    val loading = MutableLiveData(false)

    fun switchFav() {
        viewModelScope.launch {
            val old = film.value ?: return@launch
            val new = old.copy(isFavourite = !old.isFavourite)
            film.value = new
            switchFavUseCase(old)
        }
    }

    fun loadData(id: Int) {
        viewModelScope.launch {
            loading.value = true
            try {
                film.value = getFilmByIdUseCase(id)
                desc.value = getDescriptionByIdUseCase(id).description
            } catch (e: Exception) {
                e.printStackTrace()
                error.value = "Failed to load details"
            }
            loading.value = false
        }
    }
}