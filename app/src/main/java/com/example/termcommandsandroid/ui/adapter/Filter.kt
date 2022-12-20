package com.example.termcommandsandroid.ui.adapter

import android.widget.Filter
import com.example.termcommandsandroid.domain.entities.response.CategoryDetailList

open class Filter:Filter() {
    private var contactList: List<CategoryDetailList>? = null
    private var filteredContactList: ArrayList<CategoryDetailList>? = null
    private var adapter: CategoriesDetailAdapter? = null
    fun Filter(contactList: List<CategoryDetailList?>?, adapter: CategoriesDetailAdapter?) {
        this.adapter = adapter
        this.contactList = contactList as List<CategoryDetailList>?
        filteredContactList = ArrayList()
    }
    override fun performFiltering(p0: CharSequence?): FilterResults {
        filteredContactList?.clear()
        val results = FilterResults()

        //here you need to add proper items do filteredContactList

        //here you need to add proper items do filteredContactList
        for (item in contactList!!) {
            if (item.title.toLowerCase().trim().contains("pattern")) {
                filteredContactList?.add(item)
            }
        }

        results.values = filteredContactList
        results.count = filteredContactList!!.size
        return results
    }

    override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
        filteredContactList?.let { adapter?.setData(it) };
        adapter?.notifyDataSetChanged();
    }
}