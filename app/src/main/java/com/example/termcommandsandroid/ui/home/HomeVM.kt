package com.example.termcommandsandroid.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.termcommandsandroid.`interface`.AccountInterface
import com.example.termcommandsandroid.`interface`.CategoriesInterface
import com.example.termcommandsandroid.`interface`.CommandsInterface
import com.example.termcommandsandroid.base.BaseViewModel
import com.example.termcommandsandroid.domain.entities.request.AccountsRequest
import com.example.termcommandsandroid.domain.entities.response.*
import com.example.termcommandsandroid.domain.usecase.AccountUseCase
import com.example.termcommandsandroid.domain.usecase.CategoriesUseCase
import com.example.termcommandsandroid.domain.usecase.CommandUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeVM @Inject constructor(
    val accountUseCase: AccountUseCase,
    val categoriesUseCase: CategoriesUseCase,
    val commandsUseCase: CommandUseCase

)  : BaseViewModel( ) {

    val accountListInfo = MutableLiveData<AccountResponse>()
    val commandsListInfo = MutableLiveData<CategoryDetailResponse>()
    val categoriesListInfo = MutableLiveData<CategoriesResponse>()
    val failer = MutableLiveData<String>()
    fun account(accountRequest: AccountsRequest) {
        accountUseCase.account(accountRequest, object : AccountInterface {
            override fun onSuccess(data: AccountResponse) {
                accountListInfo.postValue(data)
            }

            override fun onFail(message: String) {
                failer.postValue(message)
            }

        })
    }

    fun getData() {
        categoriesUseCase.categories(object : CategoriesInterface {
            override fun onSuccess(data: CategoriesResponse) {
                categoriesListInfo.postValue(data)
            }

            override fun onFail(message: String) {
                failer.postValue(message)
            }

        })
    }

    fun search(query: String) {
        commandsUseCase.commands(query, object : CommandsInterface {
            override fun onSuccess(data: CategoryDetailResponse) {
                commandsListInfo.postValue(data)
            }

            override fun onFail(message: String) {
                failer.postValue(message)
            }

        })
    }
}