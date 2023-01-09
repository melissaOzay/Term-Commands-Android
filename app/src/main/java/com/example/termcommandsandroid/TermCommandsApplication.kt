package com.example.termcommandsandroid

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TermCommandsApplication  : Application(){
    //Bu olmazsa dagger hilt hata verir.
}