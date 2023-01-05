package com.example.termcommandsandroid.`interface`


import com.example.termcommandsandroid.domain.entities.response.CategoryDeatilResponse
import com.example.termcommandsandroid.domain.entities.response.CommandsResponse

interface CommandsInterface {
    fun onSuccess(data: CategoryDeatilResponse)
    fun onFail(message: String)
}