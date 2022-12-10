package com.example.termcommandsandroid.domain.usecase


import com.example.termcommandsandroid.AccountInterface
import com.example.termcommandsandroid.CoreLocalHelper
import com.example.termcommandsandroid.domain.entities.AccountsList
import com.example.termcommandsandroid.domain.entities.request.AccountsRequest

import com.example.termcommandsandroid.domain.repository.TermRepository
import javax.inject.Inject

 class AccountUseCase  @Inject constructor(
     private val userRepository: TermRepository,
     private val localHelper: CoreLocalHelper
 ) {
     fun loadData(request:AccountsRequest,accountInterface: AccountInterface) =
         userRepository.loadData(request,accountInterface)
     fun local(request: AccountsList){
        localHelper.saveAuthorizationToken(request.authozationKey)
     }


 }

