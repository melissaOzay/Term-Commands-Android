package com.example.termcommandsandroid.`interface`

import com.example.termcommandsandroid.domain.entities.response.LanguageResponse

interface GetLanguageInterface {
    fun onSuccess(data:LanguageResponse)
    fun onFail(message: String)
}