package com.example.termcommandsandroid.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.termcommandsandroid.R
import com.example.termcommandsandroid.domain.entities.response.CategoryDetailList

class CategoriesDetailAdapter:RecyclerView.Adapter<CategoriesDetailAdapter.CompanyViewHolder>() {
    private var categoriesList = arrayListOf<CategoryDetailList>()

    fun setData(categoriesList:ArrayList<CategoryDetailList>) {
        this.categoriesList = categoriesList
        notifyDataSetChanged()
    }

    class CompanyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameText = view.findViewById<TextView>(R.id.tvCommand)

        fun bindItems(item: CategoryDetailList) {
            nameText.text = item.title

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompanyViewHolder {
        val binding = LayoutInflater.from(parent.context)
        val view = binding.inflate(R.layout.item_categories, parent, false)
        return CompanyViewHolder(view)
    }

    override fun onBindViewHolder(holder: CompanyViewHolder, position: Int) {
        holder.bindItems(categoriesList.get(position))
    }

    override fun getItemCount(): Int {
        return categoriesList.count()
    }

}