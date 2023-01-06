package com.example.termcommandsandroid.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.termcommandsandroid.R
import com.example.termcommandsandroid.domain.entities.request.CreateCommandRequest
import com.example.termcommandsandroid.domain.entities.response.CommandsList

class AddCommandAdapter() : RecyclerView.Adapter<AddCommandAdapter.CompanyViewHolder>() {
    private var commandsList = arrayListOf<CreateCommandRequest>()

    fun setData(commandsList: ArrayList<CreateCommandRequest>) {
        this.commandsList = commandsList
        notifyDataSetChanged()
    }

    fun addData(){
        commandsList.add(CreateCommandRequest())
        notifyDataSetChanged()
    }

    class CompanyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val edtComComment = view.findViewById<TextView>(R.id.edtComComment)
        val edtComName = view.findViewById<TextView>(R.id.edtComName)
        fun bindItems(item: CreateCommandRequest) {
            edtComComment.text = item.description
            edtComName.text = item.title
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompanyViewHolder {
        val binding = LayoutInflater.from(parent.context)
        val view = binding.inflate(R.layout.item_add_command, parent, false)
        return CompanyViewHolder(view)
    }

    override fun onBindViewHolder(holder: CompanyViewHolder, position: Int) {
        holder.bindItems(commandsList.get(position))
    }

    override fun getItemCount(): Int {
        return commandsList.count()
    }
}

