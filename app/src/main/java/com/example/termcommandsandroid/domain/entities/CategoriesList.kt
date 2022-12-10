package com.example.termcommandsandroid.domain.entities

import com.google.gson.annotations.SerializedName

data class CategoriesList(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("language")
    val language: String
)
