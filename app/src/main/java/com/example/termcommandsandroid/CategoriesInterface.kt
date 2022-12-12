package com.example.termcommandsandroid

import com.example.termcommandsandroid.domain.entities.response.CategoriesResponse

interface CategoriesInterface {
    fun onSuccess(data:CategoriesResponse)
    fun onFail(message: String)
}