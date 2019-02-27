package com.example.johnqualls.recyclerviewdemo

import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter

class TestAdapter(private val viewHolderCallback: (TestAdapter, Int) -> Unit) :
        ListAdapter<Item, TestAdapter.ViewHolder>(DiffUtilCallback()) {

    class ViewHolder(var mLinearLayout: LinearLayout) : RecyclerView.ViewHolder(mLinearLayout)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.row_view, parent, false) as LinearLayout
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        val view : TextView = holder.mLinearLayout.getChildAt(0) as TextView
        Log.i(TestAdapter::class.java.name, "onBindViewHolder() -> PopulateView")
        view.text = item.name
        view.setOnClickListener{
            viewHolderCallback(this, position)
        }
    }
}