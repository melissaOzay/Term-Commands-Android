package com.example.termcommandsandroid.ui.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.termcommandsandroid.R
import com.example.termcommandsandroid.domain.entities.response.AccountsResponse
import com.example.termcommandsandroid.domain.entities.response.CategoriesResponse
import kotlin.collections.ArrayList

class AppAdapter :RecyclerView.Adapter<AppAdapter.CompanyViewHolder>() {
    private var userList = arrayListOf<CategoriesResponse>()

    fun setData(userList: ArrayList<CategoriesResponse>) {
        this.userList = userList
        userList.reverse()
        notifyDataSetChanged()
    }

    class CompanyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameText = view.findViewById<TextView>(R.id.textView)

        fun bindItems(item: CategoriesResponse) {
            nameText.text = item.data.title

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompanyViewHolder {
        val binding = LayoutInflater.from(parent.context)
        val view = binding.inflate(R.layout.item_home, parent, false)
        return CompanyViewHolder(view)
    }

    override fun onBindViewHolder(holder: CompanyViewHolder, position: Int) {
        holder.bindItems(userList.get(position))

    }

    override fun getItemCount(): Int {
        return userList.count()
    }

}