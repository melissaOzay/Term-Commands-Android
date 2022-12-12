package com.example.termcommandsandroid


import com.example.termcommandsandroid.domain.entities.response.CommandsResponse

interface CommandsInterface {
    fun onSuccess(data: CommandsResponse)
    fun onFail(message: String)
}