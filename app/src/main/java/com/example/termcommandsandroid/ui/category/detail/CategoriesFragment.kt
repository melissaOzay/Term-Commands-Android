package com.example.termcommandsandroid.ui.category.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mywords.utility.CommonUtility
import com.example.retrofitrecyclerview.ProgressBar.LoadingDialog
import com.example.termcommandsandroid.base.BaseFragment
import com.example.termcommandsandroid.databinding.FragmentCategoriesBinding
import com.example.termcommandsandroid.databinding.FragmentCommandBinding
import com.example.termcommandsandroid.domain.entities.response.CategoryDetailList
import com.example.termcommandsandroid.ui.adapter.CategoriesDetailAdapter
import com.example.termcommandsandroid.ui.adapter.CategoriesDetailListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_categories.*
import kotlinx.android.synthetic.main.fragment_categories.view.*
import kotlin.collections.ArrayList

@AndroidEntryPoint
class CategoriesFragment : BaseFragment<FragmentCategoriesBinding, CategoriesVM>() {
    override val viewModel: CategoriesVM by viewModels()
    val args: CategoriesFragmentArgs by navArgs()
    private val recyclerViewAdapter by lazy {
        CategoriesDetailAdapter(listener = object : CategoriesDetailListener {
            override fun shareButton(title: String, command: String) {
                CommonUtility.shareText(requireActivity(), title, command)
            }

            override fun copyToClipboard(title: CharSequence, command: CharSequence) {
                CommonUtility.copyText(title, command, requireActivity())
                Toast.makeText(requireContext(), "Type copied", Toast.LENGTH_SHORT).show()
            }

        })
    }
    lateinit var recyclerView: RecyclerView
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToParent: Boolean
    ): FragmentCategoriesBinding {
        return FragmentCategoriesBinding.inflate(inflater, container, attachToParent)
    }

    override fun initUI() {
        super.initUI()
        categoriesDetail()
        binding.toolbarText.searchText("Type command name or description")
        binding.toolbarText.toolbarText(args.commant)
        recyclerView = view.let { rvCategories }
        recyclerView.adapter = recyclerViewAdapter
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        binding.toolbarText.search(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.search(newText)
                return false
            }

        })

        args.let {
            viewModel.getCategoriesDetail(it.id)
        }
    }


    private fun categoriesDetail() {
        viewModel.categoriesListInfo.observe(this) {
            recyclerViewAdapter.setData(it.data as ArrayList<CategoryDetailList>)
            recyclerViewAdapter.notifyDataSetChanged()
        }

        viewModel.searchCategoriesListInfo.observe(this) {
            recyclerViewAdapter.setData(it as ArrayList<CategoryDetailList>)
        }
        viewModel.failer.observe(this) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }


    }


}