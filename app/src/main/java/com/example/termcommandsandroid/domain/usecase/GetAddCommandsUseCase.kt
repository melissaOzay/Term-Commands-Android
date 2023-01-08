package com.example.termcommandsandroid.domain.usecase

import com.example.termcommandsandroid.`interface`.CategoriesInterface
import com.example.termcommandsandroid.`interface`.GetAddCommandsInterface
import com.example.termcommandsandroid.domain.repository.TermRepository
import javax.inject.Inject

class GetAddCommandsUseCase @Inject constructor(
    private val userRepository: TermRepository
) {
    fun getAddCommands(getAddCommands: GetAddCommandsInterface) =
        userRepository.getAddCommands(getAddCommands)

}