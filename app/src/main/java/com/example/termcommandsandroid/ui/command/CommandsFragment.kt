package com.example.termcommandsandroid.ui.command

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mywords.utility.CommonUtility
import com.example.termcommandsandroid.base.BaseFragment
import com.example.termcommandsandroid.databinding.FragmentCommandBinding
import com.example.termcommandsandroid.domain.entities.response.CommandAddList
import com.example.termcommandsandroid.ui.adapter.*
import com.example.termcommandsandroid.ui.home.HomeFragmentArgs
import com.example.termcommandsandroid.ui.home.HomeFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_command.*
import kotlinx.android.synthetic.main.fragment_home.view.*

@AndroidEntryPoint
class CommandsFragment : BaseFragment<FragmentCommandBinding, CommandVM>() {
    val args: CommandsFragmentArgs by navArgs()
    private val recyclerViewAdapter by lazy {
        CommandAdapter()
    }
    override val viewModel: CommandVM by viewModels()
    lateinit var recyclerView: RecyclerView
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToParent: Boolean
    ): FragmentCommandBinding {
        return FragmentCommandBinding.inflate(inflater, container, attachToParent)
    }

    override fun initUI() {
        super.initUI()
        getAddCommands()
        viewModel.getCommands()
        recyclerView = view.let { rv }
        recyclerView.adapter = recyclerViewAdapter
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerViewAdapter.notifyDataSetChanged()
        recyclerViewAdapter.onItemClick = {
            navigate(
                CommandsFragmentDirections.actionCommandsFragmentToCommandsDetailFragment(
                    it.id,
                    it.listTitle
                )
            )
            Log.e("id", "${it.id}")

        }

    }

    override fun onStart() {
        super.onStart()
        viewModel.getCommands()
        recyclerView = view.let { rv }
        recyclerView.adapter = recyclerViewAdapter
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerViewAdapter.notifyDataSetChanged()    }

    override fun setListeners() {
        super.setListeners()
        binding.ivAddBtn.setOnClickListener {
            // showLoading()
            navigate(CommandsFragmentDirections.actionCommandsFragmentToAddCommands())
            // hideLoading()
        }
    }

    private fun getAddCommands() {
        //showLoading()
        viewModel.getAddCommands.observe(viewLifecycleOwner) {
            recyclerViewAdapter.setData(it.data as ArrayList<CommandAddList>)
            if (it.data.isEmpty()) {
                with(binding) {
                    ivEmpty.visibility = View.VISIBLE
                    tvFirst.visibility = View.VISIBLE
                    tvSecond.visibility = View.VISIBLE
                    rv.visibility = View.GONE
                }
            }
            //hideLoading()
        }

        viewModel.failer.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

    }

}