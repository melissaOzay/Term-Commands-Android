package com.example.termcommandsandroid.ui.category.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.termcommandsandroid.`interface`.CategoriesDetailInterface
import com.example.termcommandsandroid.domain.entities.response.CategoryDetailResponse
import com.example.termcommandsandroid.domain.entities.response.CategoryDetailList
import com.example.termcommandsandroid.domain.usecase.CategoriesDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class CategoriesVM @Inject constructor(
    val categoriesDetailUseCase: CategoriesDetailUseCase
) : ViewModel() {
    val categoriesListInfo = MutableLiveData<CategoryDetailResponse>()
    val searchCategoriesListInfo = MutableLiveData<List<CategoryDetailList>>()
    val failer = MutableLiveData<String>()

    fun getCategoriesDetail(categoryId: String) {
        categoriesDetailUseCase.categoriesDetail(categoryId, object : CategoriesDetailInterface {
            override fun onSuccess(data: CategoryDetailResponse) {
                categoriesListInfo.postValue(data)
            }

            override fun onFail(message: String) {
                failer.postValue(message)
            }

        })
    }

    fun search(query: String) {
        val list = categoriesListInfo.value?.data?.filter {
            it.title.contains(query)
        } ?: emptyList()
        if (query.isEmpty()) {
            categoriesListInfo.postValue(categoriesListInfo.value)
        } else {
            searchCategoriesListInfo.value = list
        }
    }
}