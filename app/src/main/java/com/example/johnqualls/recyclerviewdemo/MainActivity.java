package com.example.johnqualls.recyclerviewdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements Button.OnClickListener{
    private int count;
    private RecyclerView mRecyclerView;
    private TestAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Item> dataset;
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
        Item item = new Item("Key0", "Value0");
        dataset.add(item);

        // Counter for newly added Items
        count = 1;

        // specify an adapter (see also next example)
        mAdapter = new TestAdapter(dataset);
        mRecyclerView.setAdapter(mAdapter);
    }


    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.addItem) {
            Item item = new Item("Key" + count, "Value" + count);
            dataset.add(item);
            mAdapter.notifyItemRangeInserted(dataset.size() - 1, 1);
            count++;
        }
        else if(dataset.size() > 0){
            int removePosition = dataset.size() - 1;
            dataset.remove(removePosition);
            mRecyclerView.removeViewAt(removePosition);
            count--;
        }
    }
}
