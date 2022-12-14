package com.example.termcommandsandroid.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.termcommandsandroid.R
import com.example.termcommandsandroid.domain.entities.response.CategoriesList
import com.example.termcommandsandroid.domain.entities.request.AccountsRequest
import com.example.termcommandsandroid.ui.adapter.HomeAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.view.*

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val activityViewModel: HomeVM by viewModels()

    private val recyclerViewAdapter by lazy {
        HomeAdapter()
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
            GridLayoutManager(requireContext(), 2)
        return view
    }

    override fun onStart() {
        super.onStart()
        activityViewModel.account(AccountsRequest("", ""))
        activityViewModel.getData()

    }

    private fun categories() {
        activityViewModel.categoriesListInfo.observe(this) {
            recyclerViewAdapter.setData(it.data as ArrayList<CategoriesList>)
            Log.e("hi melisa", "gel")

        }

        activityViewModel.failer.observe(this) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

    }
}