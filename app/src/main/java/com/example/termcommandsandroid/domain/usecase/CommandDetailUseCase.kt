package com.example.termcommandsandroid.domain.usecase

import com.example.termcommandsandroid.`interface`.CommandsDetailInterface
import com.example.termcommandsandroid.domain.repository.TermRepository
import javax.inject.Inject

class CommandDetailUseCase @Inject constructor(
    private val userRepository: TermRepository
) {
    fun getCommandDetail(commandsId: String, categoriesDetailInterface: CommandsDetailInterface) =
        userRepository.getCommandsDetail(commandsId, categoriesDetailInterface)
}