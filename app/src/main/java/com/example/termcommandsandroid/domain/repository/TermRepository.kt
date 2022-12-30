package com.example.termcommandsandroid.domain.repository

import com.example.termcommandsandroid.*
import com.example.termcommandsandroid.`interface`.AccountInterface
import com.example.termcommandsandroid.`interface`.CategoriesDetailInterface
import com.example.termcommandsandroid.`interface`.CategoriesInterface
import com.example.termcommandsandroid.`interface`.CommandsInterface
import com.example.termcommandsandroid.domain.entities.request.AccountsRequest


interface TermRepository {
    fun account(createGiftRequest:AccountsRequest,accountInterface: AccountInterface)
    fun categories(accountInterface: CategoriesInterface)
    fun getCommand(commandTitle :String,commandInterface: CommandsInterface)
    fun getCategoryDetail(categoryId:String,categoriesDetailInterface: CategoriesDetailInterface)


}


