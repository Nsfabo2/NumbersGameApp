package com.example.numbersgameapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_row.view.*
import java.util.*


class RecyclerViewAdapter (private val guesses: ArrayList<Int>): RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder>(){
    class ItemViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_row,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val task  = guesses[position] //to access different colors
        holder.itemView.apply {
            GListTV.text= task.toString()
        }
    }

    override fun getItemCount(): Int {
        return guesses.size
    }

}