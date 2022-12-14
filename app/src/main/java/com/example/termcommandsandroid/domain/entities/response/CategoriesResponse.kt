package com.example.termcommandsandroid.domain.entities.response

import com.google.gson.annotations.SerializedName

data class CategoriesResponse(
    @SerializedName("code")
    val code: String,
    @SerializedName("data")
    val data: List<CategoriesList>,
    @SerializedName("error")
    val error: String,
)
