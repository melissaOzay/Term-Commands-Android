package com.example.termcommandsandroid.ui.command

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.termcommandsandroid.AccountInterface
import com.example.termcommandsandroid.CommandsInterface
import com.example.termcommandsandroid.domain.entities.request.AccountsRequest
import com.example.termcommandsandroid.domain.entities.response.AccountResponse
import com.example.termcommandsandroid.domain.entities.response.CommandsResponse
import com.example.termcommandsandroid.domain.usecase.AccountUseCase
import com.example.termcommandsandroid.domain.usecase.CommandUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CommandVM @Inject constructor(
    val commandsUseCase: CommandUseCase,
    val accountUseCase: AccountUseCase,
) : ViewModel() {

    val failer = MutableLiveData<String>()
    val commandsListInfo = MutableLiveData<CommandsResponse>()
    val accountListInfo = MutableLiveData<AccountResponse>()

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

    fun getCommands() {
        commandsUseCase.commands(object : CommandsInterface {
            override fun onSuccess(data: CommandsResponse) {
                commandsListInfo.postValue(data)
            }

            override fun onFail(message: String) {
                failer.postValue(message)
            }

        })
    }
}