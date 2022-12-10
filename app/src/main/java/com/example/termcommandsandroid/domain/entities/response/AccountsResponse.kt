package com.example.termcommandsandroid.domain.entities.response

import com.example.termcommandsandroid.domain.entities.AccountsList
import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.flow.Flow

data class AccountsResponse(
    @SerializedName("code")
    val code: String,
    @SerializedName("data")
    val data:AccountsList,
    @SerializedName("error")
    val error:String,

)