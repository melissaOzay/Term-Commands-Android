package com.example.termcommandsandroid.ui.adapter

import android.app.AlertDialog
import android.content.Context
import android.graphics.Movie
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.termcommandsandroid.R
import com.example.termcommandsandroid.domain.entities.response.CategoryDetailList
import java.util.*


class CategoriesDetailAdapter(val listener: CategoriesDetailListener) :
    RecyclerView.Adapter<CategoriesDetailAdapter.CompanyViewHolder>(),Filterable{

    private var categoriesList = arrayListOf<CategoryDetailList>()
    var photosListFiltered: ArrayList<CategoryDetailList> = ArrayList()

    fun setData(categoriesList: ArrayList<CategoryDetailList>) {
        this.categoriesList = categoriesList
        notifyDataSetChanged()
    }
    class CompanyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleText = view.findViewById<TextView>(R.id.tvtitle)
        val commandText = view.findViewById<TextView>(R.id.tvCommand)
        fun bindItems(item: CategoryDetailList) {
            titleText.text = item.title
            commandText.text = item.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompanyViewHolder {
        val binding = LayoutInflater.from(parent.context)
        val view = binding.inflate(R.layout.item_categories, parent, false)
        return CompanyViewHolder(view)
    }

    override fun onBindViewHolder(holder: CompanyViewHolder, position: Int) {
        holder.bindItems(categoriesList.get(position))
        holder.itemView.setOnClickListener {
            showUpdateDialog(
                it.context,
                holder.titleText.getText().toString(),
                holder.commandText.getText().toString()
            )

        }
    }

    private fun showUpdateDialog(contex: Context, title: String, command: String) {
        val alert = AlertDialog.Builder(contex)
        alert.setMessage(command)
        alert.setTitle(title)
        alert.setPositiveButton("Copy") { dialogInterface, i ->
            listener.copyToClipboard(title.toString(),command.toString())
        }
        alert.setNegativeButton("Share") { dialogInterface, i ->
            listener.shareButton( title .toString(),command.toString())
        }
        alert.create().show()
    }

    override fun getItemCount(): Int {
        return categoriesList.count()
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint?.toString() ?: ""
                if (charString.isEmpty()) photosListFiltered = categoriesList else {
                    val filteredList = ArrayList<CategoryDetailList>()
                    categoriesList
                        .filter {
                            (it.title.contains(constraint!!)) or
                                    (it.description.contains(constraint))

                        }
                        .forEach { filteredList.add(it) }
                    photosListFiltered = filteredList

                }
                return FilterResults().apply { values = photosListFiltered }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                categoriesList.clear()
                categoriesList.addAll(results?.values as List<CategoryDetailList>)
                notifyDataSetChanged()
            }

        }

    }

}


interface CategoriesDetailListener {
    fun shareButton(title: String,command:String)
    fun copyToClipboard(title: CharSequence,command: CharSequence)
}
