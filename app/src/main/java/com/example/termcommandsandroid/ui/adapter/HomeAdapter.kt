package com.example.termcommandsandroid.ui.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.termcommandsandroid.R
import com.example.termcommandsandroid.domain.entities.response.CategoriesList
import kotlin.collections.ArrayList

class HomeAdapter :RecyclerView.Adapter<HomeAdapter.CompanyViewHolder>() {
    private var categoriesList = arrayListOf<CategoriesList>()

    fun setData(categoriesList:ArrayList<CategoriesList>) {
        this.categoriesList = categoriesList
        notifyDataSetChanged()
    }

    class CompanyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameText = view.findViewById<TextView>(R.id.textView)

        fun bindItems(item: CategoriesList) {
            nameText.text = item.title

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompanyViewHolder {
        val binding = LayoutInflater.from(parent.context)
        val view = binding.inflate(R.layout.item_home, parent, false)
        return CompanyViewHolder(view)
    }

    override fun onBindViewHolder(holder: CompanyViewHolder, position: Int) {
        holder.bindItems(categoriesList.get(position))

    }

    override fun getItemCount(): Int {
        return categoriesList.count()
    }

}