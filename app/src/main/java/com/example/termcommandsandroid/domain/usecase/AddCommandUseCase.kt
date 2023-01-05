package com.example.termcommandsandroid.domain.usecase

import com.example.termcommandsandroid.CoreLocalHelper
import com.example.termcommandsandroid.`interface`.AddCommandsInterface
import com.example.termcommandsandroid.domain.entities.request.CommandAddRequest
import com.example.termcommandsandroid.domain.entities.request.CreateCommandRequest
import com.example.termcommandsandroid.domain.repository.TermRepository
import javax.inject.Inject

class AddCommandUseCase @Inject constructor(
    private val userRepository: TermRepository
) {
    fun Addcommands(addCommandRequest: CommandAddRequest, addCommandsInterface: AddCommandsInterface) {
        userRepository.postCommands(addCommandRequest,addCommandsInterface)
    }
}