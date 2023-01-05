package com.example.termcommandsandroid

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import javax.inject.Inject

class CoreLocalHelperImpl @Inject constructor(context: Context) : CoreLocalHelper {

    private val AUTHORIZATON_PREF_HELPER = "authorizationPref"
    lateinit var mPrefs: SharedPreferences

    init {
        mPrefs = context.getSharedPreferences("Pref", Context.MODE_PRIVATE)
    }

    override fun saveAuthorizationToken(authorizationToken: String) {
        mPrefs.edit().putString(AUTHORIZATON_PREF_HELPER, authorizationToken).apply()
    }

    override fun getAuthorizationToken(): String? {
        return mPrefs.getString(AUTHORIZATON_PREF_HELPER, "UnAuthorization")!!
    }

    override fun getCurrentLocale(): String? {
        return Resources.getSystem().getConfiguration().locale.getLanguage()
    }

}