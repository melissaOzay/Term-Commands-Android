package com.example.termcommandsandroid.spalash.screen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retrofitrecyclerview.ProgressBar.LoadingDialog
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
class SplashVM @Inject constructor(
    val accountUseCase: AccountUseCase
) : ViewModel() {
    val accountListInfo = MutableLiveData<AccountResponse>()
    val failer = MutableLiveData<String>()
    private var loadingDialog: LoadingDialog? = null
    fun account(accountRequest: AccountsRequest) {
        loadingDialog?.showLoading()
        accountUseCase.account(accountRequest, object : AccountInterface {
            override fun onSuccess(data: AccountResponse) {
                loadingDialog?.hideLoading()
                accountListInfo.postValue(data)
            }

            override fun onFail(message: String) {
                failer.postValue(message)
            }

        })
    }

}