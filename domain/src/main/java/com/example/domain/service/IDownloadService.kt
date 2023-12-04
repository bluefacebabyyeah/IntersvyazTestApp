package com.example.domain.service

interface IDownloadService {
    fun downloadImage(url: String): Boolean
}