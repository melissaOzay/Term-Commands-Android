package com.example.termcommandsandroid.domain.entities.response


import com.google.gson.annotations.SerializedName

data class CommandResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val data: Boolean,
    @SerializedName("error")
    val error: String
)