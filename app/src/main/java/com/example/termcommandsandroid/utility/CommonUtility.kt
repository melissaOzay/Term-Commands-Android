package com.example.mywords.utility

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent

object CommonUtility {
    fun shareText(activity:Activity,title:String,commant:String){
        val sharing = Intent(Intent.ACTION_SEND)
        sharing.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        sharing.type = "text/plain"
        sharing.putExtra(Intent.EXTRA_TEXT, "$title \n$commant")
        activity.startActivity(Intent.createChooser(sharing, "Share your word"))
    }

    fun copyText(title: CharSequence,commant: CharSequence,activity:Activity){
        val clipData = ClipData.newPlainText(
            "nonsense_data", "$title \n$commant")
        val clipboardManager = activity.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        clipboardManager.setPrimaryClip(clipData)
    }
}