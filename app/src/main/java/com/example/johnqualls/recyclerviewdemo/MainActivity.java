package com.example.johnqualls.recyclerviewdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // Register buttons
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
        mAdapter = new TestAdapter(dataset);
        mRecyclerView.setAdapter(mAdapter);
    }


    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.addItem) {
            dataset.add("Event" + count);
            dataset.add("Name" + count);
            mAdapter.notifyDataSetChanged();
            count++;
            Toast.makeText(this, "Add Item!", Toast.LENGTH_SHORT).show();
        }
        else {
            int position = dataset.size() - 1;
            dataset.remove(position);
            mRecyclerView.removeViewAt(position);
            mAdapter.notifyItemRemoved(position);
            mAdapter.notifyItemRangeChanged(position, dataset.size());
            Toast.makeText(this, "Remove Item!", Toast.LENGTH_SHORT).show();
        }
    }
}
