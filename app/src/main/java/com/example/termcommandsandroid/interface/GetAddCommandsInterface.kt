package com.example.termcommandsandroid.`interface`

import com.example.termcommandsandroid.domain.entities.response.CommandGetResponse

interface GetAddCommandsInterface {
    fun onSuccess(data:CommandGetResponse)
    fun onFail(message: String)
}