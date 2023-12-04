package com.example.intersvyaztestapp.di

import com.example.data.repos.FilmsRepository
import com.example.domain.repo.IFilmsRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindFilmsRepo(filmsRepository: FilmsRepository): IFilmsRepo
}