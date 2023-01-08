package com.example.termcommandsandroid.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mywords.utility.CommonUtility
import com.example.retrofitrecyclerview.ProgressBar.LoadingDialog
import com.example.termcommandsandroid.databinding.FragmentHomeBinding
import com.example.termcommandsandroid.domain.entities.request.AccountsRequest
import com.example.termcommandsandroid.domain.entities.response.*
import com.example.termcommandsandroid.ui.adapter.CategoriesDetailAdapter
import com.example.termcommandsandroid.ui.adapter.CategoriesDetailListener
import com.example.termcommandsandroid.ui.adapter.HomeAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.view.*

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var loadingDialog: LoadingDialog? = null
    private val homeFragmentViewModel: HomeVM by viewModels()
    val args: HomeFragmentArgs by navArgs()
    private lateinit var binding: FragmentHomeBinding
    private val recyclerViewAdapter by lazy {
        HomeAdapter()
    }
    private val rvCategotiesAdapter by lazy {
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


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        requireActivity().getWindow()
            .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        binding.toolbarText.searchText("Type command name or description")
        binding.toolbarText.toolbarText("Terminal Commands")
        binding.toolbarText.search(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.isNotEmpty()) {
                    search()
                    homeFragmentViewModel.search(newText)
                    recyclerView = view.rv
                    recyclerView.adapter = rvCategotiesAdapter
                    recyclerView.layoutManager =
                        LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                    rvCategotiesAdapter.notifyDataSetChanged()
                } else {
                    recyclerView = view.rv
                    homeRecyclerView()
                    recyclerViewAdapter.notifyDataSetChanged()
                }

                return false
            }

        })
        recyclerView = view.rv
        homeRecyclerView()
        recyclerViewAdapter.onItemClick = {
            val action =
                HomeFragmentDirections.actionHomeFragmentToCategoriesFragment(
                    it.id,
                    it.title
                )
            findNavController().navigate(action)
            Log.e("id", "${it.id}")

        }
        return view
    }

    fun homeRecyclerView() {
        recyclerView.adapter = recyclerViewAdapter
        recyclerView.layoutManager =
            GridLayoutManager(requireContext(), 2)
        categories()
    }

    @SuppressLint("HardwareIds")
    override fun onStart() {
        super.onStart()
        val android_id = Settings.Secure.getString(getContext()?.getContentResolver(),
            Settings.Secure.ANDROID_ID)
        homeFragmentViewModel.account(AccountsRequest("a", "$android_id"))
        homeFragmentViewModel.getData()

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

    private fun categories() {
        showLoading()
        homeFragmentViewModel.categoriesListInfo.observe(viewLifecycleOwner) {
            recyclerViewAdapter.setData(it.data as ArrayList<CategoriesList>)
            hideLoading()
        }

        homeFragmentViewModel.failer.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

    }

    private fun search() {
        homeFragmentViewModel.commandsListInfo.observe(this) {
            rvCategotiesAdapter.setData(it.data as ArrayList<CategoryDetailList>)

        }
    }
}