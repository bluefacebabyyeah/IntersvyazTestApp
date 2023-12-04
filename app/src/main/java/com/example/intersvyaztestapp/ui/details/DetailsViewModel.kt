package com.example.intersvyaztestapp.ui.details

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.FilmItem
import com.example.domain.service.IReminderService
import com.example.domain.usecases.CreateScheduledRemindUseCase
import com.example.domain.usecases.DownloadImageUseCase
import com.example.domain.usecases.GetDescriptionByIdUseCase
import com.example.domain.usecases.GetFilmByIdUseCase
import com.example.domain.usecases.ShareImageUseCase
import com.example.domain.usecases.SwitchFavUseCase
import com.example.intersvyaztestapp.services.PermissionRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getFilmByIdUseCase: GetFilmByIdUseCase,
    private val getDescriptionByIdUseCase: GetDescriptionByIdUseCase,
    private val switchFavUseCase: SwitchFavUseCase,
    private val downloadImageUseCase: DownloadImageUseCase,
    private val shareImageUseCase: ShareImageUseCase,
    private val createScheduledRemindUseCase: CreateScheduledRemindUseCase,
    private val permissionRepo: PermissionRepo,
): ViewModel() {
    val desc = MutableLiveData<String?>()
    val film = MutableLiveData<FilmItem?>()
    val error = MutableLiveData<String?>()
    val loading = MutableLiveData(false)
    val permissionsGranted = MutableLiveData<Boolean?>(null)

    fun switchFav() {
        viewModelScope.launch {
            val old = film.value ?: return@launch
            val new = old.copy(isFavourite = !old.isFavourite)
            film.value = new
            switchFavUseCase(old)
        }
    }

    fun onPermissionsGranted() {
        permissionsGranted.value = true
    }

    fun downloadImage() {
        permissionsGranted.value = permissionRepo.hasStoragePermissions()
        if (permissionRepo.hasStoragePermissions()) {
            error.value =
                if (downloadImageUseCase(film.value?.image ?: return)) "Download started"
                else "Failed to download image"
        }
    }

    fun scheduleReminder(period: IReminderService.Period) {
        createScheduledRemindUseCase(period, "aboASba")
    }

    fun share(activity: AppCompatActivity) {
        shareImageUseCase(activity, film.value?.image ?: return)
    }

    fun loadData(id: Int) {
        viewModelScope.launch {
            loading.value = true
            try {
                film.value = getFilmByIdUseCase(id)
                desc.value = getDescriptionByIdUseCase(id).description
            } catch (e: Exception) {
                e.printStackTrace()
                desc.value = null
            }
            loading.value = false
        }
    }
}