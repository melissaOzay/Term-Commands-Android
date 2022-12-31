package com.example.termcommandsandroid.ui.others

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.example.termcommandsandroid.databinding.FragmentOthersBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class OthersFragment: Fragment() {
    private lateinit var binding: FragmentOthersBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        requireActivity().getWindow()
            .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        binding = FragmentOthersBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.toolbarText.searhVisibility()
        binding.toolbarText.toolbarText("Others")
        return view
    }
}