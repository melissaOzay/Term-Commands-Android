package com.example.termcommandsandroid.domain.entities.request


import com.google.gson.annotations.SerializedName

data class CommandAddRequest(
    @SerializedName("createCommandRequest")
    val createCommandRequest: List<CreateCommandRequest>,
    @SerializedName("listTitle")
    val listTitle: String
)