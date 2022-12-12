package com.example.termcommandsandroid.domain.entities.response


import com.google.gson.annotations.SerializedName

data class AccountsList(
    @SerializedName("authozationKey")
    val authozationKey: String,
    @SerializedName("isAllowNotification")
    val isAllowNotification: Boolean,
    @SerializedName("udid")
    val udid: String,
    @SerializedName("userId")
    val userId: String
)