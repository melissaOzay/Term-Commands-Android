package com.example.termcommandsandroid.`interface`

import com.example.termcommandsandroid.domain.entities.response.CommandDetailData
import com.example.termcommandsandroid.domain.entities.response.CommandDetailResponse

interface CommandsDetailInterface {
    fun onSuccess(data: CommandDetailResponse)
    fun onFail(message: String)
}