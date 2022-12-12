package com.example.termcommandsandroid.ui.command

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.termcommandsandroid.CommandsInterface
import com.example.termcommandsandroid.domain.entities.response.CommandsResponse
import com.example.termcommandsandroid.domain.usecase.CommandUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CommandVM @Inject constructor(val commandsUseCase: CommandUseCase):ViewModel(){
    val failer = MutableLiveData<String>()
    val commandsListInfo=MutableLiveData<CommandsResponse>()
    fun getCommands(){
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