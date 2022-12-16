package com.example.termcommandsandroid.domain.usecase

import com.example.termcommandsandroid.`interface`.CategoriesDetailInterface
import com.example.termcommandsandroid.domain.repository.TermRepository
import javax.inject.Inject

class CategoriesDetailUseCase @Inject constructor(
    private val userRepository: TermRepository
) {
    fun categoriesDetail(categoryId:String,categoriesDetailInterface: CategoriesDetailInterface) =
        userRepository.getCategoryDetail(categoryId,categoriesDetailInterface)

}