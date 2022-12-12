package com.example.termcommandsandroid

import com.example.termcommandsandroid.domain.entities.response.AccountResponse


interface AccountInterface {
    fun onSuccess(data:AccountResponse)
    fun onFail(message: String)
}