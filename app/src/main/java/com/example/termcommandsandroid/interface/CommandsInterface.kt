package com.example.termcommandsandroid.`interface`


import com.example.termcommandsandroid.domain.entities.response.CategoryDetailResponse

interface CommandsInterface {
    fun onSuccess(data: CategoryDetailResponse)
    fun onFail(message: String)
}