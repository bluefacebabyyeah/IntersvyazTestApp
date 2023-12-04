package com.example.intersvyaztestapp.services

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import com.example.domain.service.IDownloadService
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class DownloadService @Inject constructor(
    @ApplicationContext private val context: Context,
): IDownloadService {
    override fun downloadImage(url: String): Boolean {
        return try {
            val manager = context.getSystemService(Context.DOWNLOAD_SERVICE) as? DownloadManager
            val uri = Uri.parse(url)
            val request = DownloadManager.Request(uri)
                .setTitle("image_${System.currentTimeMillis()}")
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                .setDestinationInExternalPublicDir(
                    Environment.DIRECTORY_DOWNLOADS,
                    "${context.packageName}_${System.currentTimeMillis()}.jpg",
                )
            manager?.enqueue(request) != null
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}