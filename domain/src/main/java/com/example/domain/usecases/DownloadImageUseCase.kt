package com.example.domain.usecases

import com.example.domain.service.IDownloadService
import javax.inject.Inject

class DownloadImageUseCase @Inject constructor(
    private val downloadService: IDownloadService,
) {
    operator fun invoke(url: String) =
        downloadService.downloadImage(url)
}