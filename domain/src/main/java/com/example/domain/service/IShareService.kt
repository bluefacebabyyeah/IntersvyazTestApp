package com.example.domain.service

import androidx.appcompat.app.AppCompatActivity

interface IShareService {
    fun share(activity: AppCompatActivity, url: String)
}