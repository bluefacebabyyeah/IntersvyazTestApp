package com.example.intersvyaztestapp.di

import com.example.domain.service.IDownloadService
import com.example.domain.service.IShareService
import com.example.intersvyaztestapp.services.DownloadService
import com.example.intersvyaztestapp.services.ShareService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ServiceModule {
    @Singleton
    @Binds
    abstract fun bindDownloadService(service: DownloadService): IDownloadService

    @Singleton
    @Binds
    abstract fun bindShareService(service: ShareService): IShareService
}