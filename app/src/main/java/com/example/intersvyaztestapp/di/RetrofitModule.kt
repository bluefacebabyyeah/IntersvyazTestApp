package com.example.intersvyaztestapp.di

import com.example.data.api.KinopoiskApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {
    companion object {
        const val API_URL="https://kinopoiskapiunofficial.tech/api/v2.2/films/"
    }

    @Provides
    fun provideApi(): KinopoiskApi =
        Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(KinopoiskApi::class.java)
}