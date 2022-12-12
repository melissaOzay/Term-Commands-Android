package com.example.termcommandsandroid.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.termcommandsandroid.AccountInterface
import com.example.termcommandsandroid.CategoriesInterface
import com.example.termcommandsandroid.domain.entities.request.AccountsRequest
import com.example.termcommandsandroid.domain.entities.response.AccountResponse
import com.example.termcommandsandroid.domain.entities.response.CategoriesResponse
import com.example.termcommandsandroid.domain.usecase.AccountUseCase
import com.example.termcommandsandroid.domain.usecase.CategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeVM @Inject constructor(
    val accountUseCase: AccountUseCase,
    val categoriesUseCase: CategoriesUseCase
) : ViewModel() {

    val accountListInfo = MutableLiveData<AccountResponse>()
    val categoriesListInfo = MutableLiveData<CategoriesResponse>()
    val failer = MutableLiveData<String>()

    fun account(accountRequest: AccountsRequest) {
        accountUseCase.account(accountRequest, object : AccountInterface {
            override fun onSuccess(data:AccountResponse) {
                accountListInfo.postValue(data)
            }

            override fun onFail(message: String) {
                failer.postValue(message)
            }

        })
    }

    fun getData() {
        categoriesUseCase.categories(object : CategoriesInterface {
            override fun onSuccess(data:CategoriesResponse) {
                categoriesListInfo.postValue(data)
            }

            override fun onFail(message: String) {
                failer.postValue(message)
            }

        })
    }


}