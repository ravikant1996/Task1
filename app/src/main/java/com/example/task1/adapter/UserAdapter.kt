package com.example.task1.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.task1.R
import com.example.task1.model.UserItem
import kotlin.collections.ArrayList

class UserAdapter(private var list: List<UserItem>) :
    RecyclerView.Adapter<UserAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        return MyViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.name.text = list[position].name
        holder.id.text = list[position].id.toString()
        holder.address.text =
            "Street: ${list[position].address.street}," +
                    " City: ${list[position].address.city}, " +
                    "zipcode: ${list[position].address.zipcode}"
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.name)
        val address: TextView = itemView.findViewById(R.id.address)
        val id: TextView = itemView.findViewById(R.id.id)
    }

    override fun getItemCount(): Int = list.size

    @SuppressLint("NotifyDataSetChanged")
    @Suppress("UNCHECKED_CAST")
    fun setFilter(filterList: List<UserItem?>) {
        list = filterList as ArrayList<UserItem>
        notifyDataSetChanged()
    }

}