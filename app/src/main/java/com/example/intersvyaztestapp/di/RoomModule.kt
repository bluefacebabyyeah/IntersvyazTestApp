package com.example.intersvyaztestapp.di

import android.content.Context
import androidx.room.Room
import com.example.data.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {
    @Provides
    @Singleton
    fun provideDb(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "db")
            .build()

    @Provides
    @Singleton
    fun provideFilmDao(db: AppDatabase) = db.getFilmDao()

    @Provides
    @Singleton
    fun provideFavDao(db: AppDatabase) = db.getFavDao()
}