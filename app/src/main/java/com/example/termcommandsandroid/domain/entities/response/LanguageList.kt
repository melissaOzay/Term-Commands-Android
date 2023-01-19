package com.example.termcommandsandroid.domain.entities.response


import com.google.gson.annotations.SerializedName

data class LanguageList(
    @SerializedName("id")
    val id: String,
    @SerializedName("language")
    val language: String,
    @SerializedName("languageShooter")
    val languageShooter: String
)