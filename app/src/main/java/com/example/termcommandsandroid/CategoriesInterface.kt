package com.example.termcommandsandroid

import com.example.termcommandsandroid.domain.entities.CategoriesList
import com.example.termcommandsandroid.domain.entities.response.CategoriesResponse

interface CategoriesInterface {
    fun onSuccess(data: List<CategoriesResponse>)
    fun onFail(message: String)
}