package com.example.termcommandsandroid.api

import com.example.termcommandsandroid.domain.entities.AccountsList
import com.example.termcommandsandroid.domain.entities.request.AccountsRequest
import com.example.termcommandsandroid.domain.entities.request.LoginRequest
import com.example.termcommandsandroid.domain.entities.response.AccountsResponse
import com.example.termcommandsandroid.domain.entities.response.CategoriesResponse
import retrofit2.Call
import retrofit2.http.*

interface RestApi {
    @POST("api/accounts")
    fun postAccounts(@Body registerRequest: AccountsRequest): Call<List<AccountsResponse>>

    @GET("api/categories")
    fun getCategories(): Call<List<CategoriesResponse>>

    @POST("/api/accounts/login")
    fun login(@Body registerRequest: LoginRequest): Call<List<AccountsResponse>>
}