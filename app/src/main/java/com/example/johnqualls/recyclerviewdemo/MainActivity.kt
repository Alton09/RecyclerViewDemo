package com.example.johnqualls.recyclerviewdemo

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity(), View.OnClickListener{
    private var count: Int = 1
    private var dataset: MutableList<Item> = mutableListOf(Item("Item1"), Item("Item2"), Item("Item3"))
    private val adapter: TestAdapter

    init {
        val viewHolderCallback = { adapter: TestAdapter, position: Int ->
            dataset.removeAt(position)
            adapter.swap(dataset.deepCopy())
        }
        adapter = TestAdapter(dataset.deepCopy(), viewHolderCallback)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        add_item.setOnClickListener(this)
        replace_items.setOnClickListener(this)
        my_recycler_view.layoutManager = LinearLayoutManager(this)
        my_recycler_view.adapter = adapter
    }


    override fun onClick(view: View) {
        if (view.id == R.id.add_item) {
            val item = Item("Item $count")
            dataset.add(0, item)
            count++
        } else { // replace all
            dataset.forEach {
                it.name = "Item ${++count}"
            }
        }
        adapter.swap(dataset)
    }
}
