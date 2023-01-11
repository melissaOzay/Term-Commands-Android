package com.example.termcommandsandroid.ui.command

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.termcommandsandroid.`interface`.GetAddCommandsInterface
import com.example.termcommandsandroid.domain.entities.response.CommandGetResponse
import com.example.termcommandsandroid.domain.entities.response.CommandsResponse
import com.example.termcommandsandroid.domain.usecase.CommandUseCase
import com.example.termcommandsandroid.domain.usecase.GetAddCommandsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CommandVM @Inject constructor(
    val getAddCommandsUseCase: GetAddCommandsUseCase
) : ViewModel() {
    val failer = MutableLiveData<String>()
    val getAddCommands = MutableLiveData<CommandGetResponse>()
    fun getCommands() {
        getAddCommandsUseCase.getAddCommands(object : GetAddCommandsInterface {
            override fun onSuccess(data: CommandGetResponse) {
                getAddCommands.postValue(data)
            }

            override fun onFail(message: String) {
                failer.postValue(message)
            }

        })
    }
}