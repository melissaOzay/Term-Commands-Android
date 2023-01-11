package com.example.termcommandsandroid.domain.entities.response


import com.google.gson.annotations.SerializedName

data class CategoryDetailResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val data: List<CategoryDetailList>,
    @SerializedName("error")
    val error: String
)