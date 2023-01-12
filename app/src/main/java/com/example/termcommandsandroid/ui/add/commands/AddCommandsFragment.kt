package com.example.termcommandsandroid.ui.add.commands

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.termcommandsandroid.base.BaseFragment
import com.example.termcommandsandroid.databinding.FragmentAddCommandsBinding
import com.example.termcommandsandroid.databinding.FragmentHomeBinding
import com.example.termcommandsandroid.dialog.ErrorDialog
import com.example.termcommandsandroid.domain.entities.request.CreateCommandRequest
import com.example.termcommandsandroid.ui.adapter.AddCommandAdapter
import com.example.termcommandsandroid.ui.command.CommandsFragmentDirections
import com.example.termcommandsandroid.ui.home.HomeVM
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_add_commands.*
import kotlinx.android.synthetic.main.fragment_add_commands.view.*


@AndroidEntryPoint
class AddCommandsFragment : BaseFragment<FragmentAddCommandsBinding, AddCommandsVM>() {
    override val viewModel: AddCommandsVM by viewModels()
    lateinit var recyclerView: RecyclerView
    private var commandsList = arrayListOf<CreateCommandRequest>()
    private val recyclerViewAdapter by lazy {
        AddCommandAdapter()
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToParent: Boolean
    ): FragmentAddCommandsBinding {
        return FragmentAddCommandsBinding.inflate(inflater, container, attachToParent)
    }

    override fun initUI() {
        super.initUI()
        recyclerView = view.let { rvAddCommand }
        recyclerView.adapter = recyclerViewAdapter
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerViewAdapter.setData(commandsList)
        recyclerViewAdapter.addData()
        binding.ivAddBtn.setOnClickListener {
            recyclerViewAdapter.addData()

        }
        binding.tvSave.setOnClickListener {
            viewModel.addCommands(
                binding.edtTerCom.text.toString(),
                recyclerViewAdapter.getCommandList()
            )
            if (binding.edtTerCom.text.toString().equals("")) {
                subscribeFail()
            } else {
                navigate(AddCommandsFragmentDirections.actionAddCommandsToCommandsFragment())
            }
        }
    }

    private fun subscribeFail() {
        viewModel.failer.observe(this.viewLifecycleOwner) {
            ErrorDialog.newInstance().show(
                childFragmentManager, ErrorDialog.Companion::class.java.simpleName
            )
        }
    }

}

