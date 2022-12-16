package com.example.termcommandsandroid.`interface`

import com.example.termcommandsandroid.domain.entities.response.CategoriesResponse

interface CategoriesInterface {
    fun onSuccess(data:CategoriesResponse)
    fun onFail(message: String)
}