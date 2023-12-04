package com.example.intersvyaztestapp.ui

import android.Manifest
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.intersvyaztestapp.R
import com.example.intersvyaztestapp.databinding.ActivityMainBinding
import com.example.intersvyaztestapp.services.EventBus
import com.example.intersvyaztestapp.services.ReminderService
import com.example.intersvyaztestapp.ui.permissions.IPermissionRequester
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), IPermissionRequester {
    @Inject lateinit var eventBus: EventBus

    private val permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission(), ::onPermissionResult)
    private var callback: (Boolean) -> Unit = {}

    companion object {
        val Fragment.permissionRequester
            get() = requireActivity() as IPermissionRequester
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navController = (supportFragmentManager.findFragmentById(R.id.fcv) as NavHostFragment).navController
        NavigationUI.setupWithNavController(binding.bnv, navController)
        val id = intent.getIntExtra(ReminderService.ID_EXTRA_KEY, -1).takeIf { it >= 0 } ?: return
        MainScope().launch {
            eventBus.emit(EventBus.Event.OpenDetailsPage(id))
        }
    }

    override fun requestStoragePermissions(callback: (Boolean) -> Unit) {
        this.callback = callback
        permissionLauncher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    }

    override fun requestPushesPermissions(callback: (Boolean) -> Unit) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            this.callback = callback
            permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }
    }

    private fun onPermissionResult(result: Boolean) = callback(result)
}