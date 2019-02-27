package com.example.johnqualls.recyclerviewdemo

import androidx.recyclerview.widget.DiffUtil

class DiffUtilCallback: DiffUtil.ItemCallback<Item>() {

    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
       return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem == newItem
    }
}