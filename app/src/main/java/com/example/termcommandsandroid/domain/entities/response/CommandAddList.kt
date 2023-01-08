package com.example.termcommandsandroid.domain.entities.response


import com.google.gson.annotations.SerializedName

data class CommandAddList(
    @SerializedName("deepLink")
    val deepLink: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("listTitle")
    val listTitle: String
)