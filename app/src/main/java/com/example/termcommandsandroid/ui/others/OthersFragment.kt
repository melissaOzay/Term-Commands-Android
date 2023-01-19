package com.example.termcommandsandroid.ui.others

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.termcommandsandroid.R
import com.example.termcommandsandroid.base.BaseFragment
import com.example.termcommandsandroid.databinding.FragmentOthersBinding
import com.example.termcommandsandroid.dialog.LanguageDialog
import com.example.termcommandsandroid.domain.entities.response.denemeList
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class OthersFragment : BaseFragment<FragmentOthersBinding, OthersVM>() {
    private val list: denemeList? = null
    override val viewModel: OthersVM by viewModels()
    override fun initUI() {
        super.initUI()
        binding.toolbarText.searhVisibility()
        context?.let { binding.toolbarText.toolbarText(it.getString(R.string.others_title)) }
        binding.tvLanguage.setOnClickListener {
            LanguageDialog.newInstance().show(
                childFragmentManager,
                LanguageDialog.javaClass.simpleName
            )

        }

            viewModel.getLanguage("en")

    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToParent: Boolean
    ): FragmentOthersBinding {
        return FragmentOthersBinding.inflate(inflater, container, attachToParent)
    }
}