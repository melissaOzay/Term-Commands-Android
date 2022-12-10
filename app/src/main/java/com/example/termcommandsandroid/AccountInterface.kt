package com.example.termcommandsandroid


import com.example.termcommandsandroid.domain.entities.response.AccountsResponse


interface AccountInterface {
    fun onSuccess(data: List<AccountsResponse>)
    fun onFail(message: String)
}