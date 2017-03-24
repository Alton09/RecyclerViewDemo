package com.example.johnqualls.recyclerviewdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements Button.OnClickListener{
    private static int count = 1;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<String> dataset;
    private Button addItem,
                   removeItem;
    private boolean clickable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // Register buttons
        clickable = true;
        addItem = (Button) findViewById(R.id.addItem);
        removeItem = (Button) findViewById(R.id.removeItem);
        addItem.setOnClickListener(this);
        removeItem.setOnClickListener(this);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // Initialize dataset
        dataset = new ArrayList<>();
        dataset.add("Event1");
        dataset.add("Bob");
        dataset.add("Event2");
        dataset.add("Jim");

        // specify an adapter (see also next example)
        mAdapter = new TestAdapter(dataset, this);
        mRecyclerView.setAdapter(mAdapter);
    }


    @Override
    public void onClick(View view) {
        if(clickable) {
            clickable = false;
            int position = dataset.size() + 1;
            if(view.getId() == R.id.addItem) {
                dataset.add("Event" + count);
                dataset.add("Name" + count);
                mAdapter.notifyItemRangeInserted(position, 1);
                count++;
                Log.i(MainActivity.class.getName(), "AddItem Clicked");
            }
            else {
                dataset.remove(position);
                dataset.remove(position - 1);
                mRecyclerView.removeViewAt(position);
                mAdapter.notifyItemRangeRemoved(position, 2);
                Log.i(MainActivity.class.getName(), "RemoveItem Clicked");
            }
        }
    }

    public boolean isClickable() {
        return clickable;
    }

    public void setClickable(boolean clickable) {
        this.clickable = clickable;
    }
}
