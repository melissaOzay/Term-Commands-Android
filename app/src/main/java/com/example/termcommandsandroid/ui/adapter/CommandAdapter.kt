package com.example.termcommandsandroid.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.termcommandsandroid.R
import com.example.termcommandsandroid.domain.entities.response.CategoriesList
import com.example.termcommandsandroid.domain.entities.response.CommandAddList
import com.example.termcommandsandroid.domain.entities.response.CommandsList

class CommandAdapter : RecyclerView.Adapter<CommandAdapter.CompanyViewHolder>() {
    private var commandsList = arrayListOf<CommandAddList>()
    var onItemClick: ((CommandAddList) -> Unit)? = null
    fun setData(commandsList: ArrayList<CommandAddList>) {
        this.commandsList = commandsList
        commandsList.reverse()
        notifyDataSetChanged()
    }

    class CompanyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameText = view.findViewById<TextView>(R.id.tvCommand)
        fun bindItems(item: CommandAddList) {
            nameText.text = item.listTitle
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompanyViewHolder {
        val binding = LayoutInflater.from(parent.context)
        val view = binding.inflate(R.layout.item_command, parent, false)
        return CompanyViewHolder(view)
    }

    override fun onBindViewHolder(holder: CompanyViewHolder, position: Int) {
        val commandDetailList=commandsList[position]
        holder.bindItems(commandsList.get(position))
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(commandDetailList)
        }
    }

    override fun getItemCount(): Int {
        return commandsList.count()
    }
}