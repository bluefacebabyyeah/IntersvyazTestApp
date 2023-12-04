package com.example.intersvyaztestapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.intersvyaztestapp.R
import com.example.intersvyaztestapp.ui.premiers.ListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().replace(R.id.fcv, ListFragment()).commit()
    }
}