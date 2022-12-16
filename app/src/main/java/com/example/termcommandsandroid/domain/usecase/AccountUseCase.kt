package com.example.termcommandsandroid.domain.usecase


import com.example.termcommandsandroid.`interface`.AccountInterface
import com.example.termcommandsandroid.CoreLocalHelper
import com.example.termcommandsandroid.domain.entities.request.AccountsRequest
import com.example.termcommandsandroid.domain.entities.response.AccountResponse

import com.example.termcommandsandroid.domain.repository.TermRepository
import javax.inject.Inject

 class AccountUseCase  @Inject constructor(
     private val termRepository: TermRepository,
     private val localHelper: CoreLocalHelper
 ) {
     fun account(request:AccountsRequest,accountInterface: AccountInterface) {
         termRepository.account(request,object : AccountInterface{
             override fun onSuccess(data: AccountResponse) {
              localHelper.saveAuthorizationToken(data.data.authozationKey)
             }

             override fun onFail(message: String) {
                 TODO("Not yet implemented")
             }

         })
     }
 }


