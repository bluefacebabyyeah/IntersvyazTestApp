package com.example.intersvyaztestapp.ui.permissions

interface IPermissionRequester {
    fun requestStoragePermissions(callback: (Boolean) -> Unit)
    fun requestPushesPermissions(callback: (Boolean) -> Unit)
}