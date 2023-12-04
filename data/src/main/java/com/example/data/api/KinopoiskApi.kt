package com.example.data.api

import com.example.data.models.FilmDescriptionDto
import com.example.data.models.FilmDto
import com.example.data.models.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface KinopoiskApi{
    @GET("premieres")
    suspend fun getPremieres(@Query("year") year: Int,
                             @Query("month") month: String,
                             @Header("x-api-key") token: String) : Response

    @GET
    suspend fun getDescriptionById(@Path ("id") id: Int): FilmDescriptionDto
}