package com.example.termcommandsandroid


import com.example.termcommandsandroid.domain.entities.response.CategoryDeatilResponse

interface CategoriesDetailInterface {
    fun onSuccess(data: CategoryDeatilResponse)
    fun onFail(message: String)
}