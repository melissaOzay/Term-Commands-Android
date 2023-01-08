package com.example.termcommandsandroid.domain.entities.response


import com.google.gson.annotations.SerializedName

data class CommandGetResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val data: List<CommandAddList>,
    @SerializedName("error")
    val error: String
)