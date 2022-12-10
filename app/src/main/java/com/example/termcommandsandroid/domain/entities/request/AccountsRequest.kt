package com.example.termcommandsandroid.domain.entities.request

import com.google.gson.annotations.SerializedName

data class AccountsRequest(
    @SerializedName("pnsToken")
    val pnsToken: String,
    @SerializedName("udid")
    val udid: String?
)
