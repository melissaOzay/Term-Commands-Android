package com.example.termcommandsandroid.domain.usecase

import com.example.termcommandsandroid.CommandsInterface
import com.example.termcommandsandroid.domain.repository.TermRepository
import javax.inject.Inject

class CommandUseCase @Inject constructor(
    private val userRepository: TermRepository
) {
    fun commands(commandInterface: CommandsInterface) =
        userRepository.getCommand( commandInterface)

}