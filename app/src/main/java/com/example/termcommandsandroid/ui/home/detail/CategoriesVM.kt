package com.example.termcommandsandroid.ui.home.detail

import androidx.lifecycle.MutableLiveData
import com.example.termcommandsandroid.`interface`.CategoriesDetailInterface
import com.example.termcommandsandroid.base.BaseViewModel
import com.example.termcommandsandroid.domain.entities.response.CategoryDetailResponse
import com.example.termcommandsandroid.domain.entities.response.CategoryDetailList
import com.example.termcommandsandroid.domain.usecase.CategoriesDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class CategoriesVM @Inject constructor(
    val categoriesDetailUseCase: CategoriesDetailUseCase
) : BaseViewModel() {
    val categoriesListInfo = MutableLiveData<CategoryDetailResponse>()
    val searchCategoriesListInfo = MutableLiveData<List<CategoryDetailList>>()
    val failer = MutableLiveData<String>()

    fun getCategoriesDetail(categoryId: String) {
        showLoading().equals(true)
        categoriesDetailUseCase.categoriesDetail(categoryId, object : CategoriesDetailInterface {
            override fun onSuccess(data: CategoryDetailResponse) {
                hideLoading().equals(false)
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
        showLoading().equals(true)
        if (query.isEmpty()) {
            hideLoading().equals(false)
            categoriesListInfo.postValue(categoriesListInfo.value)

        } else {
            hideLoading().equals(false)
            searchCategoriesListInfo.value = list
        }
    }
}