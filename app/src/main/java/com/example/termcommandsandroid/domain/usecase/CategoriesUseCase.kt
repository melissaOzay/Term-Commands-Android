package com.example.termcommandsandroid.domain.usecase

import com.example.termcommandsandroid.`interface`.CategoriesInterface
import com.example.termcommandsandroid.domain.repository.TermRepository
import javax.inject.Inject

class CategoriesUseCase @Inject constructor(
    private val userRepository: TermRepository
) {
    fun categories(categoriInterface: CategoriesInterface) =
        userRepository.categories( categoriInterface)

}