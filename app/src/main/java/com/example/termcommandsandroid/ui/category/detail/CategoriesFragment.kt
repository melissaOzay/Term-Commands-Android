package com.example.termcommandsandroid.ui.category.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mywords.utility.CommonUtility
import com.example.retrofitrecyclerview.ProgressBar.LoadingDialog
import com.example.termcommandsandroid.databinding.FragmentCategoriesBinding
import com.example.termcommandsandroid.domain.entities.request.AccountsRequest
import com.example.termcommandsandroid.domain.entities.response.CategoryDetailList
import com.example.termcommandsandroid.ui.adapter.CategoriesDetailAdapter
import com.example.termcommandsandroid.ui.adapter.CategoriesDetailListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_categories.view.*
import java.util.*
import java.util.Locale.filter
import kotlin.collections.ArrayList

@AndroidEntryPoint
class CategoriesFragment : Fragment() {
    private lateinit var binding: FragmentCategoriesBinding
    private var loadingDialog: LoadingDialog? = null
    private val categoriesViewModel: CategoriesVM by viewModels()
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        categoriesDetail()

        args.let {
            categoriesViewModel.getCategoriesDetail(it.id)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        requireActivity().getWindow()
            .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.toolbarText.searchText("Type command name or description")
        binding.toolbarText.toolbarText(args.commant)
        recyclerView = view.rvCategories
        recyclerView.adapter = recyclerViewAdapter
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        binding.toolbarText.search(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                    categoriesViewModel.search(newText)
                return false
            }

        })

        return view
    }


    fun showLoading() {
        if (loadingDialog == null) {
            loadingDialog = LoadingDialog(requireContext())
        }

        loadingDialog?.apply {
            if (isShowing.not()) {
                show()
            }
        }
    }

    fun hideLoading() {
        loadingDialog?.dismiss()
    }

    private fun categoriesDetail() {
        showLoading()
        categoriesViewModel.categoriesListInfo.observe(this) {
            recyclerViewAdapter.setData(it.data as ArrayList<CategoryDetailList>)
            recyclerViewAdapter.notifyDataSetChanged()
            hideLoading()
        }

        categoriesViewModel.searchcategoriesListInfo.observe(this){
            recyclerViewAdapter.setData(it as  ArrayList<CategoryDetailList>)
        }
        categoriesViewModel.failer.observe(this) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }


    }


}