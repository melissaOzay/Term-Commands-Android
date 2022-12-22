package com.example.termcommandsandroid.ui.category.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.termcommandsandroid.`interface`.AccountInterface
import com.example.termcommandsandroid.`interface`.CategoriesDetailInterface
import com.example.termcommandsandroid.domain.entities.request.AccountsRequest
import com.example.termcommandsandroid.domain.entities.response.AccountResponse
import com.example.termcommandsandroid.domain.entities.response.CategoriesList
import com.example.termcommandsandroid.domain.entities.response.CategoryDeatilResponse
import com.example.termcommandsandroid.domain.entities.response.CategoryDetailList
import com.example.termcommandsandroid.domain.usecase.AccountUseCase
import com.example.termcommandsandroid.domain.usecase.CategoriesDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class CategoriesVM @Inject constructor(
    val categoriesDetailUseCase: CategoriesDetailUseCase
) : ViewModel() {
    val categoriesListInfo = MutableLiveData<CategoryDeatilResponse>()
    val searchcategoriesListInfo = MutableLiveData<List<CategoryDetailList>>()
    val failer = MutableLiveData<String>()

    fun getCategoriesDetail(categoryId:String) {
        categoriesDetailUseCase.categoriesDetail(categoryId,object : CategoriesDetailInterface {
            override fun onSuccess(data: CategoryDeatilResponse) {
                categoriesListInfo.postValue(data)
            }

            override fun onFail(message: String) {
                failer.postValue(message)
            }

        })
    }

    fun search(query:String){
        val list = categoriesListInfo.value?.data?.filter {
            it.title.contains(query)
        } ?: emptyList()
        if (query.isEmpty()){
            categoriesListInfo.postValue(categoriesListInfo.value)
        }else{
            searchcategoriesListInfo.value = list
        }
    }
}