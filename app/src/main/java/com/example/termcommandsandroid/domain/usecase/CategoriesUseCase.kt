package com.example.termcommandsandroid.domain.usecase

import com.example.termcommandsandroid.CategoriesInterface
import com.example.termcommandsandroid.domain.entities.response.CategoriesResponse
import com.example.termcommandsandroid.domain.repository.TermRepository
import javax.inject.Inject

class CategoriesUseCase @Inject constructor(
    private val userRepository: TermRepository
) {
    fun categories(categoriInterface: CategoriesInterface) =
        userRepository.categories( categoriInterface)

}