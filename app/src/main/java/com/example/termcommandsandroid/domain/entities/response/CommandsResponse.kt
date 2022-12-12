package com.example.termcommandsandroid.domain.entities.response


import com.google.gson.annotations.SerializedName

data class CommandsResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val data: List<CommandsList>,
    @SerializedName("error")
    val error: String
)