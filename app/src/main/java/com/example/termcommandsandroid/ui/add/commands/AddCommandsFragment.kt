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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.termcommandsandroid.databinding.FragmentAddCommandsBinding
import com.example.termcommandsandroid.dialog.ErrorDialog
import com.example.termcommandsandroid.domain.entities.request.CommandAddRequest
import com.example.termcommandsandroid.domain.entities.request.CreateCommandRequest
import com.example.termcommandsandroid.ui.adapter.AddCommandAdapter
import com.example.termcommandsandroid.ui.adapter.AddCommandInterface
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_add_commands.view.*


@AndroidEntryPoint
class AddCommandsFragment : Fragment() {
    private val viewModel: AddCommandsVM by viewModels()
    private lateinit var binding: FragmentAddCommandsBinding
    lateinit var recyclerView: RecyclerView
    private var commandsList = arrayListOf<CreateCommandRequest>()
    private val recyclerViewAdapter by lazy {
        AddCommandAdapter(listener = object : AddCommandInterface {
            override fun empty(text1: String, text2: String) {
                errorMessage(text1, text2)
            }

        })
    }


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
            Toast.makeText(requireContext(), "ba??ar??l??", Toast.LENGTH_SHORT).show()
        }
        recyclerView = view.rvAddCommand
        recyclerView.adapter = recyclerViewAdapter
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerViewAdapter.setData(commandsList)
        recyclerViewAdapter.addData()

        binding.ivAddBtn.setOnClickListener {
            recyclerViewAdapter.addData()

        }

        return view
    }

    fun errorMessage(text1: String, text2: String) {
        binding.tvSave.setOnClickListener {
            recyclerView.adapter = recyclerViewAdapter
            recyclerView.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            recyclerViewAdapter.setData(arrayListOf())
            var title = binding.edtTerCom.text.toString()

            if (title.isEmpty() || text1.isEmpty() || text2.isEmpty()) {
                ErrorDialog.newInstance().show(
                    childFragmentManager, ErrorDialog.Companion::class.java.simpleName
                )
            } else if (title.isNotEmpty() && text1.isNotEmpty() && text2.isNotEmpty()) {
                viewModel.addCommands(CommandAddRequest(listOf(CreateCommandRequest(text1,"",text2)), title))
                val action =
                    AddCommandsFragmentDirections.actionAddCommandsToCommandsFragment()
                findNavController().navigate(action)
                recyclerViewAdapter.notifyDataSetChanged()

            }
        }
    }

    private fun commands() {
        viewModel.addCommands.observe(viewLifecycleOwner, {
            Toast.makeText(requireContext(), "ba??ar??l??", Toast.LENGTH_SHORT).show()
            with(it) {
                data.equals(true)
                code.equals(0)
                error.equals("hatal??")
            }

        })

        viewModel.failer.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

    }
}