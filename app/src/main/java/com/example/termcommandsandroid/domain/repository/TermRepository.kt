package com.example.termcommandsandroid.domain.repository

import com.example.termcommandsandroid.`interface`.*
import com.example.termcommandsandroid.domain.entities.request.AccountsRequest
import com.example.termcommandsandroid.domain.entities.request.CommandAddRequest


interface TermRepository {
    fun account(createGiftRequest:AccountsRequest,accountInterface: AccountInterface)
    fun getCategories(accountInterface: CategoriesInterface)
    fun getCategoryDetail(categoryId:String,categoriesDetailInterface: CategoriesDetailInterface)
    fun getCommand(commandTitle :String,commandInterface: CommandsInterface)
    fun postCommands(addCommandRequest: CommandAddRequest, addCommandsInterface: AddCommandsInterface)
    fun getAddCommands(getAddCommandsInterface:GetAddCommandsInterface)
    fun getCommandsDetail(commandId:String,categoriesDetailInterface:CommandsDetailInterface)
    fun getLanguage(getAddCommandsInterface:GetLanguageInterface)


}


