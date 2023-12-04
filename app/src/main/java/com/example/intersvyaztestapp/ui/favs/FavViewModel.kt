package com.example.intersvyaztestapp.ui.favs

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.FilmItem
import com.example.domain.usecases.GetAllFavsUseCase
import com.example.domain.usecases.SwitchFavUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavViewModel @Inject constructor(
    private val getAllFavs: GetAllFavsUseCase,
    private val switchFavUseCase: SwitchFavUseCase,
): ViewModel() {
    val films = MutableLiveData<MutableList<FilmItem>>()
    val lastModifiedIndex = MutableLiveData<Int?>()
    val loading = MutableLiveData(false)

    fun loadItems() {
        viewModelScope.launch {
            loading.value = true
            films.value = getAllFavs().toMutableList()
            loading.value = false
        }
    }

    fun onSwitchFav(item: FilmItem) {
        viewModelScope.launch {
            val old = films.value?.firstOrNull { it.id == item.id } ?: return@launch
            val new = old.copy(isFavourite = !old.isFavourite)
            val index = films.value?.indexOfFirst { it.id == item.id } ?: return@launch
            films.value?.run {
                removeAt(index)
                add(index, new)
            }
            lastModifiedIndex.value = index
            switchFavUseCase.invoke(old)
        }
    }
}