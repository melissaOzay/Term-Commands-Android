package com.example.termcommandsandroid.domain.entities.response


import com.google.gson.annotations.SerializedName

data class LanguageResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val data: List<LanguageList>,
    @SerializedName("error")
    val error: String
)