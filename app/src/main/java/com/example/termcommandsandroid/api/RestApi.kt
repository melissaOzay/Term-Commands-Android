package com.example.termcommandsandroid.api

import com.example.termcommandsandroid.domain.entities.request.AccountsRequest
import com.example.termcommandsandroid.domain.entities.request.LoginRequest
import com.example.termcommandsandroid.domain.entities.response.AccountResponse
import com.example.termcommandsandroid.domain.entities.response.CategoriesResponse
import com.example.termcommandsandroid.domain.entities.response.CommandsResponse
import retrofit2.Call
import retrofit2.http.*

interface RestApi {
    @POST("api/accounts")
    fun postAccounts(@Body registerRequest: AccountsRequest): Call<AccountResponse>

    @GET("api/categories")
    fun getCategories(): Call<CategoriesResponse>

    @GET("/api/commands")
    fun getCommands(): Call<CommandsResponse>
}