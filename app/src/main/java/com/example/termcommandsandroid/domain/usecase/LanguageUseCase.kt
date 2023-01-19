package com.example.termcommandsandroid.domain.usecase

import com.example.termcommandsandroid.CoreLocalHelper
import com.example.termcommandsandroid.`interface`.GetLanguageInterface
import com.example.termcommandsandroid.domain.entities.response.LanguageResponse
import com.example.termcommandsandroid.domain.entities.response.denemeList
import com.example.termcommandsandroid.domain.repository.TermRepository
import javax.inject.Inject

class LanguageUseCase @Inject constructor(
    private val userRepository: TermRepository,
    private val localHelper: CoreLocalHelper
) {
    fun getLanguage(language:String,getAddCommandsInterface: GetLanguageInterface) {
        userRepository.getLanguage(getAddCommandsInterface)
            localHelper.saveCurrentLocale(language)
    }
}
