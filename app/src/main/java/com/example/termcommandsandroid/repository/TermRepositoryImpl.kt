package com.example.termcommandsandroid.repository

import com.example.termcommandsandroid.`interface`.*
import com.example.termcommandsandroid.api.RestApi
import com.example.termcommandsandroid.domain.entities.request.AccountsRequest
import com.example.termcommandsandroid.domain.entities.request.CommandAddRequest
import com.example.termcommandsandroid.domain.entities.response.*
import com.example.termcommandsandroid.domain.repository.TermRepository
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
    override fun getCategories(categoriesInterface: CategoriesInterface) {
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
    override fun getCommand(commandTitle :String,commandInterface: CommandsInterface) {
        apiService.getCategoriesDetail(commandTitle).enqueue(object : Callback<CategoryDetailResponse> {
            override fun onResponse(
                call: Call<CategoryDetailResponse>,
                response: Response<CategoryDetailResponse>
            ) {
                if (response.isSuccessful) {
                    commandInterface.onSuccess(response.body()!!)
                }
            }

            override fun onFailure(call: Call<CategoryDetailResponse>, t: Throwable) {
                commandInterface.onFail(t.localizedMessage!!)
            }
        })
    }

    override fun postCommands(addCommandRequest:CommandAddRequest, addCommandsInterface: AddCommandsInterface) {
        apiService.postCategories(addCommandRequest).enqueue(object : Callback<CommandResponse> {
            override fun onResponse(
                call: Call<CommandResponse>,
                response: Response<CommandResponse>
            ) {
                if (response.isSuccessful) {
                    addCommandsInterface.onSuccess(response.body()!!)
                }
            }

            override fun onFailure(call: Call<CommandResponse>, t: Throwable) {
                addCommandsInterface.onFail(t.localizedMessage!!)
            }
        })
    }

    override fun getCategoryDetail(categoryId: String,categoriesDetailInterface: CategoriesDetailInterface) {
        apiService.getCategoryDetail(categoryId).enqueue(object : Callback<CategoryDetailResponse> {
            override fun onResponse(
                call: Call<CategoryDetailResponse>,
                response: Response<CategoryDetailResponse>
            ) {
                if (response.isSuccessful) {
                    categoriesDetailInterface.onSuccess(response.body()!!)
                }
            }

            override fun onFailure(call: Call<CategoryDetailResponse>, t: Throwable) {
                categoriesDetailInterface.onFail(t.localizedMessage!!)
            }

        })
    }

    override fun getAddCommands(getAddCommands: GetAddCommandsInterface) {
        apiService.getCategoriesCommand().enqueue(object : Callback<CommandGetResponse> {
            override fun onResponse(
                call: Call<CommandGetResponse>,
                response: Response<CommandGetResponse>
            ) {
                if (response.isSuccessful) {
                    getAddCommands.onSuccess(response.body()!!)
                }
            }

            override fun onFailure(call: Call<CommandGetResponse>, t: Throwable) {
                getAddCommands.onFail(t.localizedMessage!!)
            }
        })
    }


}

