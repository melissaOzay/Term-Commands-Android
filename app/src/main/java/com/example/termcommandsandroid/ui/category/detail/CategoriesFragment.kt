package com.example.termcommandsandroid.ui.category.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.termcommandsandroid.R
import com.example.termcommandsandroid.domain.entities.request.AccountsRequest
import com.example.termcommandsandroid.domain.entities.response.CategoryDetailList
import com.example.termcommandsandroid.ui.adapter.CategoriesDetailAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_categories.view.*
import kotlinx.android.synthetic.main.fragment_home.view.*

@AndroidEntryPoint
class CategoriesFragment : Fragment() {

    private val categoriesViewModel: CategoriesVM by viewModels()
    val args: CategoriesFragmentArgs by navArgs()
    private val recyclerViewAdapter by lazy {
        CategoriesDetailAdapter()
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
        val view = inflater.inflate(R.layout.fragment_categories, container, false)

        requireActivity().getWindow()
            .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)

        recyclerView = view.rvCategories
        recyclerView.adapter = recyclerViewAdapter
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        return view
    }
    override fun onStart() {
        super.onStart()
        categoriesViewModel.account(AccountsRequest("", ""))

    }
    private fun categoriesDetail(){
        categoriesViewModel.categoriesListInfo.observe(this){
            recyclerViewAdapter.setData(it.data as ArrayList<CategoryDetailList> )
        }
        categoriesViewModel.failer.observe(this) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }

}