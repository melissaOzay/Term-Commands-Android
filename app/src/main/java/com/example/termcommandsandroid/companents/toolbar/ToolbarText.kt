package com.example.termcommandsandroid.companents.toolbar

import android.content.Context
import android.location.GnssAntennaInfo
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.SearchView
import com.example.termcommandsandroid.R
import com.example.termcommandsandroid.databinding.ToolbarTextBinding
import com.example.termcommandsandroid.domain.entities.response.CategoryDetailList
import com.example.termcommandsandroid.ui.adapter.CategoriesDetailAdapter

class ToolbarText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(
    context, attrs
) {
    val binding: ToolbarTextBinding = ToolbarTextBinding.inflate(
        LayoutInflater.from(context), this, true
    )

    fun toolbarText(text: String) {
        binding.tvToolbar.setText(text)
    }

    fun searchText(text: String) {
        binding.searchView.queryHint = text.toString()

    }
    fun search(listener: SearchView.OnQueryTextListener){
        binding.searchView.setOnQueryTextListener(listener)

    }

}
