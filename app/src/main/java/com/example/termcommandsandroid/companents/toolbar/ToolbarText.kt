package com.example.termcommandsandroid.companents.toolbar

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.SearchView
import androidx.core.view.size
import com.example.termcommandsandroid.databinding.ToolbarTextBinding


class ToolbarText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(
    context, attrs
) {
    val binding: ToolbarTextBinding = ToolbarTextBinding.inflate(
        LayoutInflater.from(context), this, true
    )

    fun toolbarText(text:String) {
        binding.tvToolbar.setText(text)
    }

    fun searchText(text:String) {
        binding.searchView.queryHint = text
            binding.searchView.width

    }
    fun searhVisibility(){
        binding.searchView.visibility = View.GONE
    }
    fun search(listener: SearchView.OnQueryTextListener){
        binding.searchView.setOnQueryTextListener(listener)

    }

}
