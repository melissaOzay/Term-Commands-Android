package com.example.termcommandsandroid.domain.repository

import com.example.termcommandsandroid.`interface`.*
import com.example.termcommandsandroid.domain.entities.request.AccountsRequest
import com.example.termcommandsandroid.domain.entities.request.CommandAddRequest


interface TermRepository {
    fun account(createGiftRequest:AccountsRequest,accountInterface: AccountInterface)
    fun categories(accountInterface: CategoriesInterface)
    fun getCommand(commandTitle :String,commandInterface: CommandsInterface)
    fun postCommands(addCommandRequest: CommandAddRequest, addCommandsInterface: AddCommandsInterface)
    fun getCategoryDetail(categoryId:String,categoriesDetailInterface: CategoriesDetailInterface)
    fun getAddCommands(getAddCommands:GetAddCommandsInterface)


}


