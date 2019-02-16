package com.example.johnqualls.recyclerviewdemo

import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView

class TestAdapter(private val mDataset: MutableList<Item>) :
        RecyclerView.Adapter<TestAdapter.ViewHolder>() {

    class ViewHolder(var mLinearLayout: LinearLayout) : RecyclerView.ViewHolder(mLinearLayout)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.row_view, parent, false) as LinearLayout
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mDataset[position]

        Log.i(TestAdapter::class.java.name, "onBindViewHolder() -> PopulateView")
        (holder.mLinearLayout.getChildAt(0) as TextView).text = item.key
        (holder.mLinearLayout.getChildAt(1) as TextView).text = item.value
    }

    override fun getItemCount(): Int {
        return mDataset.size
    }

}