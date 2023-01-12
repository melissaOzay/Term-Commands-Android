package com.example.termcommandsandroid.ui.add.commands

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.termcommandsandroid.`interface`.AddCommandsInterface
import com.example.termcommandsandroid.base.BaseViewModel
import com.example.termcommandsandroid.domain.entities.request.CommandAddRequest
import com.example.termcommandsandroid.domain.entities.request.CreateCommandRequest
import com.example.termcommandsandroid.domain.entities.response.CommandResponse
import com.example.termcommandsandroid.domain.usecase.AddCommandUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddCommandsVM @Inject constructor(
    val addCommandsUseCase: AddCommandUseCase
) : BaseViewModel() {
    val addCommands = MutableLiveData<CommandResponse>()
    val failer = MutableLiveData<String>()
    fun addCommands(listTitle: String, commands: List<CreateCommandRequest>) {
        if (listTitle.isEmpty()){
            failer.postValue("Başlık boş girilemez")
        }else{
            showLoading().equals(true)
            val request = CommandAddRequest(
                listTitle = listTitle,
                createCommandRequest = commands
            )
            addCommandsUseCase.Addcommands(request, object : AddCommandsInterface {
                override fun onSuccess(data: CommandResponse) {
                    hideLoading().equals(false)
                    addCommands.postValue(data)
                }

                override fun onFail(message: String) {
                    failer.postValue(message)
                }

            })
        }


    }

}