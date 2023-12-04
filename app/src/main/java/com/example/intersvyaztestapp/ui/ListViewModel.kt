package com.example.intersvyaztestapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.api.KinopoiskApi
import com.example.domain.models.FilmItem
import com.example.domain.usecases.GetFilmsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val getFilmsUseCase: GetFilmsUseCase
): ViewModel() {
    val items = MutableLiveData<MutableList<FilmItem>>()
    val modifiedIndex = MutableLiveData<Int?>()

     fun loadItems(){
         viewModelScope.launch {
             items.value = getFilmsUseCase.invoke().toMutableList()
         }
     }

    fun onItemClick(item: FilmItem){
        val foundItem = items.value?.firstOrNull{
            item.title == it.title
        }?: return

        val updated = foundItem.copy(
            isChecked = !foundItem.isChecked
        )

        val index = items.value?.indexOfFirst {
            it.title == item.title
        }?: return

        items.value?.removeAt(index)
        items.value?.add(index, updated)
        modifiedIndex.value = index
    }
}