package com.example.data.models

data class Response(
    val total : Int,
    val items: List<FilmDto>
)