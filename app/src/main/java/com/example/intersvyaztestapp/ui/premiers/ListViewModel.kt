package com.example.intersvyaztestapp.ui.premiers

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.FilmItem
import com.example.domain.usecases.GetFilmsUseCase
import com.example.domain.usecases.SaveCacheUseCase
import com.example.domain.usecases.SwitchFavUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val getFilmsUseCase: GetFilmsUseCase,
    private val switchFavUseCase: SwitchFavUseCase,
    private val saveCacheUseCase: SaveCacheUseCase,
): ViewModel() {
    val items = MutableLiveData<MutableList<FilmItem>>()
    val modifiedIndex = MutableLiveData<Int?>()
    val error = MutableLiveData<String?>()
    val loading = MutableLiveData(false)

     fun loadItems() {
         viewModelScope.launch {
             loading.value = true
             try {
                 val newItems = getFilmsUseCase.invoke(false).toMutableList()
                 items.value = newItems
                 saveCacheUseCase(newItems)
             } catch (e: Exception) {
                 e.printStackTrace()
                 items.value = getFilmsUseCase.invoke(true).toMutableList()
                 error.value = "An error has occurred"
                 error.value = null
             }
             loading.value = false
         }
     }

    fun onItemClick(item: FilmItem) {
        viewModelScope.launch {
            val foundItem = items.value?.firstOrNull {
                item.title == it.title
            } ?: return@launch

            val updated = foundItem.copy(
                isFavourite = !foundItem.isFavourite
            )

            val index = items.value?.indexOfFirst {
                it.title == item.title
            } ?: return@launch

            items.value?.removeAt(index)
            items.value?.add(index, updated)
            modifiedIndex.value = index
            switchFavUseCase(item)
        }
    }
}