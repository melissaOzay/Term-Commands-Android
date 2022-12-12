package com.example.termcommandsandroid.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.termcommandsandroid.AccountInterface
import com.example.termcommandsandroid.CategoriesInterface
import com.example.termcommandsandroid.domain.entities.AccountsList
import com.example.termcommandsandroid.domain.entities.request.AccountsRequest
import com.example.termcommandsandroid.domain.entities.response.AccountsResponse
import com.example.termcommandsandroid.domain.entities.response.CategoriesResponse
import com.example.termcommandsandroid.domain.usecase.AccountUseCase
import com.example.termcommandsandroid.domain.usecase.CategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeVM @Inject constructor(
    val userUseCase: AccountUseCase,
    val categoriesUseCase: CategoriesUseCase
) : ViewModel() {

    val userListInfo = MutableLiveData<List<AccountsResponse>>()
    val categoriesListInfo = MutableLiveData<List<CategoriesResponse>>()
    val failer = MutableLiveData<String>()
    val fail = MutableLiveData<String>()

    fun loadData(accountRequest: AccountsRequest) {
        userUseCase.loadData(accountRequest, object : AccountInterface {
            override fun onSuccess(data: List<AccountsResponse>) {
                userListInfo.postValue(data)
                Log.e("hi melisa", "gel")
            }

            override fun onFail(message: String) {
                failer.postValue(message)
            }

        })
    }

    fun getData() {
        categoriesUseCase.categories(object : CategoriesInterface {
            override fun onSuccess(data: List<CategoriesResponse>) {
                categoriesListInfo.postValue(data)
                Log.e("hi melisa", "gel")
            }

            override fun onFail(message: String) {
                fail.postValue(message)
            }

        })
    }


}