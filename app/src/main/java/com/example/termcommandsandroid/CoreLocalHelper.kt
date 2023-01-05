package com.example.termcommandsandroid

import android.annotation.TargetApi
import android.content.res.Resources
import android.os.Build
import android.util.Log
import java.util.*


interface CoreLocalHelper {
    /**
     * Authorization Key'i kaydetmek için kullanılır
     * @param authorizationToken: Kaydedilen authorization key değeridir
     */
    fun saveAuthorizationToken(authorizationToken: String)

    /**
     * Kaydedilen authorization keyi döner
     */
    fun getAuthorizationToken(): String?

    fun getCurrentLocale(): String?



}