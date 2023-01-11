package com.example.termcommandsandroid.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mywords.utility.CommonUtility
import com.example.retrofitrecyclerview.ProgressBar.LoadingDialog
import com.example.termcommandsandroid.base.BaseFragment
import com.example.termcommandsandroid.databinding.FragmentHomeBinding
import com.example.termcommandsandroid.domain.entities.request.AccountsRequest
import com.example.termcommandsandroid.domain.entities.response.*
import com.example.termcommandsandroid.ui.adapter.CategoriesDetailAdapter
import com.example.termcommandsandroid.ui.adapter.CategoriesDetailListener
import com.example.termcommandsandroid.ui.adapter.HomeAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeVM>() {
    val args: HomeFragmentArgs by navArgs()
    private val recyclerViewAdapter by lazy {
        HomeAdapter()
    }

    override val viewModel: HomeVM by viewModels()
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

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToParent: Boolean
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, attachToParent)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            toolbarText.searchText("Type command name or description")
            toolbarText.toolbarText("Terminal Commands")
            toolbarText.search(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(p0: String): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    if (newText.isNotEmpty()) {
                        search()
                        viewModel?.search(newText)
                        recyclerView = view.let { rv }
                        recyclerView.adapter = rvCategotiesAdapter
                        recyclerView.layoutManager =
                            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                        rvCategotiesAdapter.notifyDataSetChanged()
                    } else {
                        recyclerView = view.let { rv }
                        homeRecyclerView()
                        recyclerViewAdapter.notifyDataSetChanged()
                    }

                    return false
                }
        })

        }
        recyclerView = view.let { rv }
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
        viewModel?.account(AccountsRequest("a", "$android_id"))
        viewModel?.getData()

    }

    private fun categories() {
        viewModel?.categoriesListInfo?.observe(viewLifecycleOwner) {
            recyclerViewAdapter.setData(it.data as ArrayList<CategoriesList>)
        }

        viewModel?.failer?.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

    }

    private fun search() {
        viewModel?.commandsListInfo?.observe(this) {
            rvCategotiesAdapter.setData(it.data as ArrayList<CategoryDetailList>)

        }
    }

}