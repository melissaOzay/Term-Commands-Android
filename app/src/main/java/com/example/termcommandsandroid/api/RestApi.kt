package com.example.termcommandsandroid.api

import com.example.termcommandsandroid.domain.entities.request.AccountsRequest
import com.example.termcommandsandroid.domain.entities.request.CommandAddRequest
import com.example.termcommandsandroid.domain.entities.request.CreateCommandRequest
import com.example.termcommandsandroid.domain.entities.response.*
import retrofit2.Call
import retrofit2.http.*

interface RestApi {
    @POST("api/accounts")
    fun postAccounts(@Body registerRequest: AccountsRequest): Call<AccountResponse>

    @GET("api/categories")
    fun getCategories(): Call<CategoriesResponse>

    @GET("api/commands")
    fun getCommands(@Query("commandTitle") commandTitle : String): Call<CategoryDeatilResponse>

    @POST("api/list")
    fun postCategories(@Body addCommandRequest:CommandAddRequest): Call<CommandResponse>

    @GET("/api/list")
    fun getCategoriesCommand(): Call<CommandGetResponse>

    @GET("api/categories/{categoryId}/commands")
    fun getCategoryDetail(@Path("categoryId") categoryId: String): Call<CategoryDeatilResponse>
}