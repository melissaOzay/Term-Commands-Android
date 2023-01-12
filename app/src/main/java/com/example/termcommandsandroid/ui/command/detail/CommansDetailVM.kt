package com.example.termcommandsandroid.ui.command.detail

import androidx.lifecycle.MutableLiveData
import com.example.termcommandsandroid.`interface`.CommandsDetailInterface
import com.example.termcommandsandroid.base.BaseViewModel
import com.example.termcommandsandroid.domain.entities.response.CommandDetailResponse
import com.example.termcommandsandroid.domain.usecase.CommandDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class CommansDetailVM @Inject constructor(
    val getCommandDetailUseCase: CommandDetailUseCase
) : BaseViewModel() {
    val getCommandDetailInfo = MutableLiveData<CommandDetailResponse>()
    val failer = MutableLiveData<String>()

    fun getCommandsDetail(commandId: String) {
        showLoading().equals(true)
        getCommandDetailUseCase.getCommandDetail(commandId, object : CommandsDetailInterface {
            override fun onSuccess(data: CommandDetailResponse) {
                hideLoading().equals(false)
                getCommandDetailInfo.postValue(data)

            }

            override fun onFail(message: String) {
                failer.postValue(message)
            }

        })
    }

}