package com.example.intersvyaztestapp.services

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import com.example.domain.service.IShareService
import javax.inject.Inject

class ShareService @Inject constructor(): IShareService {
    override fun share(activity: AppCompatActivity, url: String) {
        val sharingIntent = Intent(Intent.ACTION_SEND)
        val screenshotUri = Uri.parse(url)
        sharingIntent.type = "image/jpeg"
        sharingIntent.putExtra(Intent.EXTRA_STREAM, screenshotUri)
        activity.startActivity(Intent.createChooser(sharingIntent, "Share image using"))
    }
}