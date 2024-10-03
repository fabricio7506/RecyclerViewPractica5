package com.example.seagradece2



import java.io.Serializable

data class Song(
    val name: String,
    val imageResId: Int,
    val duration: String,
    val audioResId: Int
) : Serializable
