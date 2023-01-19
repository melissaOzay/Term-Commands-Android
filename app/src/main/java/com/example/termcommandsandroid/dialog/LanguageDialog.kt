package com.example.termcommandsandroid.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.termcommandsandroid.databinding.DialogLanguageBinding
import com.example.termcommandsandroid.domain.entities.response.denemeList


class LanguageDialog : DialogFragment(){
    lateinit var dialogBinding: DialogLanguageBinding
    override fun onStart() {
        super.onStart()
        dialog?.getWindow()
            ?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog?.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.getWindow()?.setGravity(Gravity.BOTTOM)
        dialogBinding.tvFr.setOnClickListener {
            denemeList("fr")
            Log.e("not","fr")
        }
        dialogBinding.tvTr.setOnClickListener {
            denemeList("tr")
            Log.e("not","tr")
        }
        dialogBinding.tvEn.setOnClickListener {
            denemeList("en")
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dialogBinding = DialogLanguageBinding
            .inflate(LayoutInflater.from(context))
        return dialogBinding.root


    }


    companion object {
        fun newInstance() = LanguageDialog().apply {
            return this
        }
    }
    }
