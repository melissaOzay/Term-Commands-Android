package com.example.termcommandsandroid.ui.add.commands

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.termcommandsandroid.CoreLocalHelper
import com.example.termcommandsandroid.`interface`.AddCommandsInterface
import com.example.termcommandsandroid.domain.entities.request.CommandAddRequest
import com.example.termcommandsandroid.domain.entities.request.CreateCommandRequest
import com.example.termcommandsandroid.domain.entities.response.CommandResponse
import com.example.termcommandsandroid.domain.usecase.AddCommandUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddCommandsVM @Inject constructor( val addCommandsUseCase: AddCommandUseCase
) : ViewModel() {
    val addCommands=MutableLiveData<CommandResponse>()
    val failer = MutableLiveData<String>()
    fun addCommands(addCommandRequest: CommandAddRequest){
        addCommandsUseCase.Addcommands(addCommandRequest, object :AddCommandsInterface{
            override fun onSuccess(data: CommandResponse) {
                addCommands.postValue(data)
            }

            override fun onFail(message: String) {
                failer.postValue(message)
            }

        })
    }

}