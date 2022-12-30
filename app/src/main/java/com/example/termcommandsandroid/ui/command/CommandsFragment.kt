package com.example.termcommandsandroid.ui.command

import android.os.Bundle
import android.text.TextUtils.isEmpty
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.core.view.isEmpty
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.termcommandsandroid.R
import com.example.termcommandsandroid.databinding.ActivityMainBinding
import com.example.termcommandsandroid.databinding.FragmentCommandBinding
import com.example.termcommandsandroid.domain.entities.request.AccountsRequest
import com.example.termcommandsandroid.domain.entities.response.CategoriesList
import com.example.termcommandsandroid.domain.entities.response.CommandsList
import com.example.termcommandsandroid.ui.adapter.CommandAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.view.*

@AndroidEntryPoint
class CommandsFragment : Fragment() {
    private val viewModel: CommandVM by viewModels()
    private lateinit var binding:FragmentCommandBinding
    private val recyclerViewAdapter by lazy {
        CommandAdapter()
    }

    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        commends()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        requireActivity().getWindow()
            .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        binding = FragmentCommandBinding.inflate(inflater, container, false)
        val view = binding.root
        recyclerView = view.rv
        recyclerView.adapter = recyclerViewAdapter
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        if(recyclerViewAdapter.itemCount==0){
            binding.constraint.visibility  = View.VISIBLE
            binding.rv.visibility =View.GONE
        }else{
            binding.constraint.visibility = View.GONE
            binding.rv.visibility =View.VISIBLE
        }
        return view
    }

    override fun onStart() {
        super.onStart()

    }

    private fun commends() {
        viewModel.commandsListInfo.observe(this) {
            recyclerViewAdapter.setData(it.data as ArrayList<CommandsList>)

        }

        viewModel.failer.observe(this) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

    }
}