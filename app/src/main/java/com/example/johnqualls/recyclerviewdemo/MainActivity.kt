package com.example.johnqualls.recyclerviewdemo

import android.app.Activity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.widget.Button

class MainActivity : Activity(), View.OnClickListener{
    private var count: Int = 0
    private var mRecyclerView: RecyclerView? = null
    private var mAdapter: TestAdapter? = null
    private var mLayoutManager: RecyclerView.LayoutManager? = null
    private var dataset: MutableList<Item> = mutableListOf(Item("Key0", "Value0"))
    private var addItem: Button? = null
    private var removeItem: Button? = null
    private var clickable: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mRecyclerView = findViewById(R.id.my_recycler_view) as RecyclerView

        // Register buttons
        clickable = true
        addItem = findViewById(R.id.addItem) as Button
        removeItem = findViewById(R.id.removeItem) as Button
        addItem!!.setOnClickListener(this)
        removeItem!!.setOnClickListener(this)

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView!!.setHasFixedSize(true)

        // use a linear layout manager
        mLayoutManager = LinearLayoutManager(this)
        mRecyclerView!!.layoutManager = mLayoutManager

        // Counter for newly added Items
        count = 1

        // specify an adapter (see also next example)
        mAdapter = TestAdapter(dataset)
        mRecyclerView!!.adapter = mAdapter
    }


    override fun onClick(view: View) {
        if (view.id == R.id.addItem) {
            val item = Item("Key$count", "Value$count")
            dataset!!.add(item)
            mAdapter!!.notifyItemRangeInserted(dataset!!.size - 1, 1)
            count++
        } else if (dataset!!.size > 0) {
            val removePosition = dataset!!.size - 1
            dataset!!.removeAt(removePosition)
            mRecyclerView!!.removeViewAt(removePosition)
            count--
        }
    }
}
