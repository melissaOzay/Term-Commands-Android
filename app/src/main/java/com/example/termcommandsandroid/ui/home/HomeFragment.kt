package com.example.termcommandsandroid.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.termcommandsandroid.ui.adapter.AppAdapter
import com.example.termcommandsandroid.R
import com.example.termcommandsandroid.domain.entities.AccountsList
import com.example.termcommandsandroid.domain.entities.CategoriesList
import com.example.termcommandsandroid.domain.entities.request.AccountsRequest
import com.example.termcommandsandroid.domain.entities.response.AccountsResponse
import com.example.termcommandsandroid.domain.entities.response.CategoriesResponse
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.view.*

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val activityViewModel: HomeVM by viewModels()

    private val recyclerViewAdapter by lazy {
        AppAdapter()
    }
    lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        categories()


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        requireActivity().getWindow()
            .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)

        recyclerView = view.rv
        recyclerView.adapter = recyclerViewAdapter

        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        return view
    }

    override fun onStart() {
        super.onStart()
        activityViewModel.loadData(AccountsRequest("",""))
        activityViewModel.getData()

    }

    private fun categories() {
        activityViewModel.categoriesListInfo.observe(this) {
            recyclerViewAdapter.setData(it as ArrayList<CategoriesResponse>)
            Log.e("hi melisa", "gel")

        }

        activityViewModel.fail.observe(this) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

    }
}