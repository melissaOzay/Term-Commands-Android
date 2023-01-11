package com.example.termcommandsandroid.ui.command

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
import com.example.retrofitrecyclerview.ProgressBar.LoadingDialog
import com.example.termcommandsandroid.databinding.FragmentCommandBinding
import com.example.termcommandsandroid.domain.entities.request.AccountsRequest
import com.example.termcommandsandroid.domain.entities.response.CategoriesList
import com.example.termcommandsandroid.domain.entities.response.CommandAddList
import com.example.termcommandsandroid.domain.entities.response.CommandsList
import com.example.termcommandsandroid.ui.adapter.CommandAdapter
import com.example.termcommandsandroid.ui.home.HomeFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.view.*

@AndroidEntryPoint
class CommandsFragment : Fragment() {
    private val viewModel: CommandVM by viewModels()
    private var loadingDialog: LoadingDialog? = null
    private lateinit var binding: FragmentCommandBinding
    private val recyclerViewAdapter by lazy {
        CommandAdapter()
    }

    lateinit var recyclerView: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        requireActivity().getWindow()
            .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        binding = FragmentCommandBinding.inflate(inflater, container, false)
        val view = binding.root
        getAddCommands()
        viewModel.getCommands()
        recyclerView = view.rv
        recyclerView.adapter = recyclerViewAdapter
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerViewAdapter.notifyDataSetChanged()

        binding.ivAddBtn.setOnClickListener {
            showLoading()
            val action =
                CommandsFragmentDirections.actionCommandsFragmentToAddCommands()
            findNavController().navigate(action)
            hideLoading()
        }


        return view
    }

    fun showLoading() {
        if (loadingDialog == null) {
            loadingDialog = LoadingDialog(requireContext())
        }
        loadingDialog?.showLoading()
    }

    fun hideLoading() {
        loadingDialog?.hideLoading()
    }

    private fun getAddCommands() {
        showLoading()
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

            hideLoading()
        }

        viewModel.failer.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

    }
}