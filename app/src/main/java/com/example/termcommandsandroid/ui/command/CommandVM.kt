package com.example.termcommandsandroid.ui.command

import androidx.lifecycle.MutableLiveData
import com.example.termcommandsandroid.`interface`.GetAddCommandsInterface
import com.example.termcommandsandroid.base.BaseViewModel
import com.example.termcommandsandroid.domain.entities.response.CommandGetResponse
import com.example.termcommandsandroid.domain.usecase.GetAddCommandsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CommandVM @Inject constructor(
    val getAddCommandsUseCase: GetAddCommandsUseCase
) : BaseViewModel() {
    val failer = MutableLiveData<String>()
    val getAddCommands = MutableLiveData<CommandGetResponse>()
    fun getCommands() {
        showLoading().equals(true)
        getAddCommandsUseCase.getAddCommands(object : GetAddCommandsInterface {
            override fun onSuccess(data: CommandGetResponse) {
                hideLoading().equals(false)
                getAddCommands.postValue(data)
            }

            override fun onFail(message: String) {
                failer.postValue(message)
            }

        })
    }

}