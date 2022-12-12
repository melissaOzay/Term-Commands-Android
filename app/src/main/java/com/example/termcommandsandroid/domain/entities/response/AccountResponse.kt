package com.example.termcommandsandroid.domain.entities.response


import com.google.gson.annotations.SerializedName

data class AccountResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val data: AccountsList,
    @SerializedName("error")
    val error: String
)