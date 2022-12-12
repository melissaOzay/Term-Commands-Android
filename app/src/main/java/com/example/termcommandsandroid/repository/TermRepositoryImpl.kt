package com.example.termcommandsandroid.repository

import com.example.termcommandsandroid.AccountInterface
import com.example.termcommandsandroid.CategoriesInterface
import com.example.termcommandsandroid.CommandsInterface
import com.example.termcommandsandroid.api.RestApi
import com.example.termcommandsandroid.domain.entities.request.AccountsRequest
import com.example.termcommandsandroid.domain.entities.response.AccountResponse
import com.example.termcommandsandroid.domain.repository.TermRepository
import com.example.termcommandsandroid.domain.entities.response.CategoriesResponse
import com.example.termcommandsandroid.domain.entities.response.CommandsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class TermRepositoryImpl @Inject constructor(private val apiService: RestApi): TermRepository {

    override fun account(createGiftRequest: AccountsRequest, accountInterface: AccountInterface) {
        apiService.postAccounts(createGiftRequest).enqueue(object : Callback<AccountResponse> {
            override fun onResponse(
                call: Call<AccountResponse>,
                response: Response<AccountResponse>
            ) {
                if (response.isSuccessful) {
                    accountInterface.onSuccess(response.body()!!)
                }
            }

            override fun onFailure(call: Call<AccountResponse>, t: Throwable) {
                accountInterface.onFail(t.localizedMessage!!)
            }
        })
    }

    override fun categories(categoriesInterface: CategoriesInterface) {
        apiService.getCategories().enqueue(object : Callback<CategoriesResponse> {
            override fun onResponse(
                call: Call<CategoriesResponse>,
                response: Response<CategoriesResponse>
            ) {
                if (response.isSuccessful) {
                    categoriesInterface.onSuccess(response.body()!!)
                }
            }

            override fun onFailure(call: Call<CategoriesResponse>, t: Throwable) {
                categoriesInterface.onFail(t.localizedMessage!!)
            }
        })
    }

    override fun getCommand(commandInterface: CommandsInterface) {
        apiService.getCommands().enqueue(object : Callback<CommandsResponse> {
            override fun onResponse(
                call: Call<CommandsResponse>,
                response: Response<CommandsResponse>
            ) {
                if (response.isSuccessful) {
                    commandInterface.onSuccess(response.body()!!)
                }
            }

            override fun onFailure(call: Call<CommandsResponse>, t: Throwable) {
                commandInterface.onFail(t.localizedMessage!!)
            }
        })
    }
}

