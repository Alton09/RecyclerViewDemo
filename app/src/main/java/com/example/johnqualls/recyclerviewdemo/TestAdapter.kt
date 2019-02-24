package com.example.johnqualls.recyclerviewdemo

import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil

class TestAdapter(private val items: MutableList<Item>, private val viewHolderCallback: (TestAdapter, Int) -> Unit) :
        RecyclerView.Adapter<TestAdapter.ViewHolder>() {

    class ViewHolder(var mLinearLayout: LinearLayout) : RecyclerView.ViewHolder(mLinearLayout)

    fun swap(newItems: MutableList<Item>) {
        val result = DiffUtil.calculateDiff(DiffUtilCallback(items, newItems))
        items.clear()
        items.addAll(newItems.deepCopy())
        result.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.row_view, parent, false) as LinearLayout
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        val view : TextView = holder.mLinearLayout.getChildAt(0) as TextView
        Log.i(TestAdapter::class.java.name, "onBindViewHolder() -> PopulateView")
        view.text = item.name
        view.setOnClickListener{
            viewHolderCallback(this, position)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

}