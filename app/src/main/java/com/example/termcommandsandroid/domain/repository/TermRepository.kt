package com.example.termcommandsandroid.domain.repository

import com.example.termcommandsandroid.AccountInterface
import com.example.termcommandsandroid.CategoriesDetailInterface
import com.example.termcommandsandroid.CategoriesInterface
import com.example.termcommandsandroid.CommandsInterface
import com.example.termcommandsandroid.domain.entities.request.AccountsRequest


interface TermRepository {
    fun account(createGiftRequest:AccountsRequest,accountInterface:AccountInterface)
    fun categories(accountInterface:CategoriesInterface)
    fun getCommand(commandInterface:CommandsInterface)
    fun getCategoryDetail(categoryId:String,categoriesDetailInterface: CategoriesDetailInterface)


}


