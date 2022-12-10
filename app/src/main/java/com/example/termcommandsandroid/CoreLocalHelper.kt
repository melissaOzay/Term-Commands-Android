package com.example.termcommandsandroid

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

}