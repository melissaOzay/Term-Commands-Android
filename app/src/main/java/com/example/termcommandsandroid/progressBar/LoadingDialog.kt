package com.example.retrofitrecyclerview.ProgressBar

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.view.WindowManager
import com.example.termcommandsandroid.R


class LoadingDialog(context: Context) : Dialog(context){

    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        if (window != null) {
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            window?.setLayout(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT
            )
            window?.setDimAmount(0f)
        }
        setCanceledOnTouchOutside(false)
        setCancelable(false)
        setContentView(R.layout.dialog_loading)
    }

}