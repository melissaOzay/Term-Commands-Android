package com.example.termcommandsandroid.repository

import com.example.termcommandsandroid.AccountInterface
import com.example.termcommandsandroid.CategoriesInterface
import com.example.termcommandsandroid.api.RestApi
import com.example.termcommandsandroid.domain.entities.request.AccountsRequest
import com.example.termcommandsandroid.domain.repository.TermRepository
import com.example.termcommandsandroid.domain.entities.response.AccountsResponse
import com.example.termcommandsandroid.domain.entities.response.CategoriesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class TermRepositoryImpl @Inject constructor(private val apiService: RestApi): TermRepository {

    override fun loadData(createGiftRequest: AccountsRequest, interfac: AccountInterface) {
        apiService.postAccounts(createGiftRequest).enqueue(object : Callback<List<AccountsResponse>> {
            override fun onResponse(
                call: Call<List<AccountsResponse>>,
                response: Response<List<AccountsResponse>>
            ) {
                if (response.isSuccessful) {
                    interfac.onSuccess(response.body()!!)
                }
            }

            override fun onFailure(call: Call<List<AccountsResponse>>, t: Throwable) {
                interfac.onFail(t.localizedMessage!!)
            }
        })
    }

    override fun categories(list: CategoriesResponse, accountInterface: CategoriesInterface) {
        apiService.getCategories().enqueue(object : Callback<List<CategoriesResponse>> {
            override fun onResponse(
                call: Call<List<CategoriesResponse>>,
                response: Response<List<CategoriesResponse>>
            ) {
                if (response.isSuccessful) {
                    accountInterface.onSuccess(response.body()!!)
                }
            }

            override fun onFailure(call: Call<List<CategoriesResponse>>, t: Throwable) {
                accountInterface.onFail(t.localizedMessage!!)
            }
        })
    }
}

