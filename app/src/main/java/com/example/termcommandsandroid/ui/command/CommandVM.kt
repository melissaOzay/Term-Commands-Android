package com.example.termcommandsandroid.ui.command

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.termcommandsandroid.domain.entities.response.CommandsResponse
import com.example.termcommandsandroid.domain.usecase.CommandUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CommandVM @Inject constructor(
    val commandsUseCase: CommandUseCase,
) : ViewModel() {

    val failer = MutableLiveData<String>()
    val commandsListInfo = MutableLiveData<CommandsResponse>()




}