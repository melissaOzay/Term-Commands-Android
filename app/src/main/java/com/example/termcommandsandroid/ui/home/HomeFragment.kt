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
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.termcommandsandroid.databinding.FragmentHomeBinding
import com.example.termcommandsandroid.domain.entities.response.CategoriesList
import com.example.termcommandsandroid.domain.entities.request.AccountsRequest
import com.example.termcommandsandroid.domain.entities.response.CategoriesResponse
import com.example.termcommandsandroid.ui.adapter.HomeAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.view.*

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeFragmentViewModel: HomeVM by viewModels()
    val args: HomeFragmentArgs by navArgs()
    private lateinit var binding: FragmentHomeBinding
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
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        requireActivity().getWindow()
            .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)

        recyclerView = view.rv
        recyclerView.adapter = recyclerViewAdapter
        recyclerView.layoutManager =
            GridLayoutManager(requireContext(), 2)

        recyclerViewAdapter.onItemClick={
            val action =
                HomeFragmentDirections.actionHomeFragmentToCategoriesFragment(it.id)
            findNavController().navigate(action)
            Log.e("id","${it.id}")

        }
        return view
    }
    override fun onStart() {
        super.onStart()
        homeFragmentViewModel.account(AccountsRequest("", ""))
        homeFragmentViewModel.getData()

    }

    private fun categories() {
        homeFragmentViewModel.categoriesListInfo.observe(this) {
            recyclerViewAdapter.setData(it.data as ArrayList<CategoriesList>)

        }

        homeFragmentViewModel.failer.observe(this) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

    }
}