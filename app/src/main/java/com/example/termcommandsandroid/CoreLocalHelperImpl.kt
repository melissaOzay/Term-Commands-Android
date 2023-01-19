package com.example.termcommandsandroid

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import javax.inject.Inject

class CoreLocalHelperImpl @Inject constructor(context: Context) : CoreLocalHelper {
    private val AUTHORIZATON_PREF_HELPER = "authorizationPref"
    private val LANGUAGE = "LANGUAGE"
    lateinit var mPrefs: SharedPreferences
    lateinit var mPref: SharedPreferences

    init {
        mPrefs = context.getSharedPreferences("Pref", Context.MODE_PRIVATE)
        mPref = context.getSharedPreferences("Pref", Context.MODE_PRIVATE)
    }

    override fun saveAuthorizationToken(authorizationToken: String) {
        mPrefs.edit().putString(AUTHORIZATON_PREF_HELPER, authorizationToken).apply()
    }

    override fun getAuthorizationToken(): String? {
        return mPrefs.getString(AUTHORIZATON_PREF_HELPER, "UnAuthorization")!!
    }

    override fun getCurrentLocale(): String? {
        return mPref.getString(LANGUAGE, "LANGUAGE")!!
    }

    override fun saveCurrentLocale(l: String) {
        mPrefs.edit().putString(LANGUAGE, l).apply()
    }

}