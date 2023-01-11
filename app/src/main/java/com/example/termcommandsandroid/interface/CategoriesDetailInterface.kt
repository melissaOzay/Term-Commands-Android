package com.example.termcommandsandroid.`interface`


import com.example.termcommandsandroid.domain.entities.response.CategoryDetailResponse

interface CategoriesDetailInterface {
    fun onSuccess(data: CategoryDetailResponse)
    fun onFail(message: String)
}