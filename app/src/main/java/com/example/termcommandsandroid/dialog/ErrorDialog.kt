package com.example.termcommandsandroid.dialog


import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import com.example.termcommandsandroid.databinding.DialogErrorBinding

class ErrorDialog : DialogFragment() {

    lateinit var binding: DialogErrorBinding

    override fun onStart() {
        super.onStart()
        dialog?.getWindow()
            ?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog?.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogErrorBinding
            .inflate(LayoutInflater.from(context))
        return binding.root
    }

    companion object {
        fun newInstance() = ErrorDialog()
    }
}
