package com.example.domain.usecases

import androidx.appcompat.app.AppCompatActivity
import com.example.domain.service.IShareService
import javax.inject.Inject

class ShareImageUseCase @Inject constructor(
    private val shareService: IShareService,
) {
    operator fun invoke(activity: AppCompatActivity, url: String) =
        shareService.share(activity, url)
}