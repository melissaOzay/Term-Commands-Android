package com.example.termcommandsandroid.domain.usecase

import com.example.termcommandsandroid.`interface`.CommandsInterface
import com.example.termcommandsandroid.domain.repository.TermRepository
import javax.inject.Inject

class CommandUseCase @Inject constructor(
    private val userRepository: TermRepository
) {
    fun commands(name:String,commandInterface: CommandsInterface) =
        userRepository.getCommand(name,commandInterface)

}