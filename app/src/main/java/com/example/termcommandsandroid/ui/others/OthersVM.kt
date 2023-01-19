package com.example.termcommandsandroid.ui.others

import androidx.lifecycle.MutableLiveData
import com.example.termcommandsandroid.`interface`.GetLanguageInterface
import com.example.termcommandsandroid.base.BaseViewModel
import com.example.termcommandsandroid.domain.entities.response.CommandDetailResponse
import com.example.termcommandsandroid.domain.entities.response.LanguageList
import com.example.termcommandsandroid.domain.entities.response.LanguageResponse
import com.example.termcommandsandroid.domain.entities.response.denemeList
import com.example.termcommandsandroid.domain.usecase.CommandDetailUseCase
import com.example.termcommandsandroid.domain.usecase.LanguageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import org.intellij.lang.annotations.Language
import javax.inject.Inject

@HiltViewModel
class OthersVM @Inject constructor(
    val getLanguageUseCase: LanguageUseCase
) : BaseViewModel() {
    val getLanguageInfo = MutableLiveData<LanguageResponse>()
    val failer = MutableLiveData<String>()
    fun getLanguage(language:String) {
            getLanguageUseCase.getLanguage(language,object : GetLanguageInterface {
                override fun onSuccess(data: LanguageResponse) {
                    getLanguageInfo.postValue(data)
                }

                override fun onFail(message: String) {
                    failer.postValue(message)
                }

            })
        }
    }
