package com.example.termcommandsandroid.domain.entities.response


import com.google.gson.annotations.SerializedName

data class CommandDetailData(
    @SerializedName("commandListDTO")
    val commandListDTO: List<CommandDetailList>,
    @SerializedName("deepLink")
    val deepLink: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("listTitle")
    val listTitle: String)