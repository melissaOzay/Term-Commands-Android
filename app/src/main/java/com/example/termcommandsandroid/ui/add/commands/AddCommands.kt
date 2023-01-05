package com.example.termcommandsandroid.ui.add.commands

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.termcommandsandroid.CoreLocalHelper
import com.example.termcommandsandroid.databinding.FragmentAddCommandsBinding
import com.example.termcommandsandroid.domain.entities.request.CommandAddRequest
import com.example.termcommandsandroid.domain.entities.request.CreateCommandRequest
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AddCommands : Fragment() {
    private val viewModel: AddCommandsVM by viewModels()
    private lateinit var binding: FragmentAddCommandsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddCommandsBinding.inflate(inflater, container, false)
        val view = binding.root
        requireActivity().getWindow()
            .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)

        commands()
        binding.tvSave.setOnClickListener {
            Toast.makeText(requireContext(), "başarılı", Toast.LENGTH_SHORT).show()
            addCommands()
        }

        return view
    }

    fun addCommands() {
        val terminalCm = binding.edtTerCom.text.toString()
        val cmTitle = binding.edtComComment.text.toString()
        val cmDescription = binding.edtComName.text.toString()
        val language = getCurrentLocale()
        val addComment = CreateCommandRequest(cmTitle, language.toString(), cmDescription)
        val listAddCommand = CommandAddRequest(listOf(addComment), terminalCm)
        viewModel.addCommands(listAddCommand)
    }

    fun getCurrentLocale(): String? {
        return Resources.getSystem().getConfiguration().locale.getLanguage()
    }

    private fun commands() {
        viewModel.addCommands.observe(viewLifecycleOwner, {
            Toast.makeText(requireContext(), "başarılı", Toast.LENGTH_SHORT).show()
            with(it) {
                data.equals(true)
                code.equals(0)
                error.equals("hatalı")
            }

        })

        viewModel.failer.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

    }
}