package com.example.termcommandsandroid.domain.repository

import com.example.termcommandsandroid.AccountInterface
import com.example.termcommandsandroid.CategoriesInterface
import com.example.termcommandsandroid.domain.entities.CategoriesList
import com.example.termcommandsandroid.domain.entities.request.AccountsRequest
import com.example.termcommandsandroid.domain.entities.response.CategoriesResponse


interface TermRepository {
    fun loadData(createGiftRequest:AccountsRequest,accountInterface:AccountInterface)
    fun categories(list:CategoriesResponse,accountInterface:CategoriesInterface)
}


