package com.example.termcommandsandroid.`interface`

import com.example.termcommandsandroid.domain.entities.response.CommandResponse


interface AddCommandsInterface {
    fun onSuccess(data: CommandResponse)
    fun onFail(message: String)
}