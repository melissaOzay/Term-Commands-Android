package com.example.termcommandsandroid.ui.command.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mywords.utility.CommonUtility
import com.example.termcommandsandroid.R
import com.example.termcommandsandroid.base.BaseFragment
import com.example.termcommandsandroid.databinding.FragmentCommandDetailBinding
import com.example.termcommandsandroid.domain.entities.response.CommandDetailList
import com.example.termcommandsandroid.ui.adapter.CommandDetailListener
import com.example.termcommandsandroid.ui.adapter.CommandsDetailAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_command_detail.*
import kotlin.collections.ArrayList

@AndroidEntryPoint
class CommandsDetailFragment : BaseFragment<FragmentCommandDetailBinding, CommansDetailVM>() {
    override val viewModel: CommansDetailVM by viewModels()
    val args: CommandsDetailFragmentArgs by navArgs()
    private val recyclerViewAdapter by lazy {
        CommandsDetailAdapter(listener = object : CommandDetailListener {
            override fun shareButton(title: String, command: String) {
                CommonUtility.shareText(requireActivity(), title, command)
            }

            override fun copyToClipboard(title: CharSequence, command: CharSequence) {
                CommonUtility.copyText(title, command, requireActivity())
                Toast.makeText(requireContext(), R.string.commmand_message, Toast.LENGTH_SHORT).show()
            }

        })
    }
    lateinit var recyclerView: RecyclerView
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToParent: Boolean
    ): FragmentCommandDetailBinding {
        return FragmentCommandDetailBinding.inflate(inflater, container, attachToParent)
    }

    override fun initUI() {
        super.initUI()
        categoriesDetail()
        binding.toolbarText.toolbarText(args.commant)
        binding.toolbarText.searhVisibility()
        recyclerView = view.let { rvCommand }
        recyclerView.adapter = recyclerViewAdapter
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        args.let {
            viewModel.getCommandsDetail(it.id)
        }
    }


    private fun categoriesDetail() {
        viewModel.getCommandDetailInfo.observe(this) {
            recyclerViewAdapter.setData(it.data.commandListDTO as ArrayList<CommandDetailList>)
            recyclerViewAdapter.notifyDataSetChanged()
        }

        viewModel.failer.observe(this) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }


    }


}