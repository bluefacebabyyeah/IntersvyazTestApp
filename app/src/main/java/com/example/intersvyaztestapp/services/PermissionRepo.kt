package com.example.intersvyaztestapp.services

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.content.ContextCompat
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PermissionRepo @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    fun hasStoragePermissions() =
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q ||
            hasPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)

    private fun hasPermission(permission: String) =
        ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED
}