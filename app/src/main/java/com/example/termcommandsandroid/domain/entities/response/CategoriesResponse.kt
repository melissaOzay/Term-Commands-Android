package com.example.termcommandsandroid.domain.entities.response

import com.example.termcommandsandroid.domain.entities.CategoriesList
import com.google.gson.annotations.SerializedName

data class CategoriesResponse(
    @SerializedName("code")
    val code: String,
    @SerializedName("data")
    val data: CategoriesList,
    @SerializedName("error")
    val error: String,
)
