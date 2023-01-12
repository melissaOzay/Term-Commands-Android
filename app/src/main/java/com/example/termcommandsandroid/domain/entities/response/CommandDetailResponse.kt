package com.example.termcommandsandroid.domain.entities.response


import com.google.gson.annotations.SerializedName

data class CommandDetailResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val data: CommandDetailData,
    @SerializedName("error")
    val error: String
)