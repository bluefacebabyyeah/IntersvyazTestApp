package com.example.intersvyaztestapp.ui

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.intersvyaztestapp.R
import com.example.intersvyaztestapp.databinding.ActivityMainBinding
import com.example.intersvyaztestapp.ui.permissions.IPermissionRequester
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), IPermissionRequester {
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
    }

    override fun requestStoragePermissions(callback: (Boolean) -> Unit) {
        this.callback = callback
        permissionLauncher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    }

    private fun onPermissionResult(result: Boolean) = callback(result)
}