package com.example.termcommandsandroid.domain.entities.request


import com.google.gson.annotations.SerializedName

data class CreateCommandRequest(
    @SerializedName("description")
    var description: String? = "",
    @SerializedName("language")
    val language: String? = "",
    @SerializedName("title")
    var title: String? = ""
)