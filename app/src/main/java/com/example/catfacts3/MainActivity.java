package com.example.catfacts3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    String[] mCatFacts;
    CatFactViewModel mCatFactViewModel;
    RecyclerView mRecyclerView;
    private final int NUM_FACTS = 20;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.cat_fact_recycler_view);
        CatFactAdapter adapter = new CatFactAdapter(NUM_FACTS, (catFact, author) -> {
            Intent intent = new Intent(MainActivity.this, CatFactActivity.class);
            intent.putExtra("fact", catFact);
            intent.putExtra("author", author);
            startActivity(intent);
        });
        mCatFactViewModel = new ViewModelProvider(this).get(CatFactViewModel.class);
        if(mCatFactViewModel.catFact.getValue().size() == 0) {
            mCatFactViewModel.catFact.observe(this, catFactList -> {
                adapter.setCatFacts(catFactList);
                if (catFactList.size() >= NUM_FACTS) {
                    displayCatFacts(adapter);
                }
            });
            mCatFactViewModel.getCatFact(NUM_FACTS);
        }
        else {
            adapter.setCatFacts(mCatFactViewModel.catFact.getValue());
            displayCatFacts(adapter);
        }
    }
    void displayCatFacts(RecyclerView.Adapter adapter) {
        mRecyclerView.setVisibility(View.VISIBLE);
        ((TextView) findViewById(R.id.tv_cat_fact_loading)).setVisibility(View.INVISIBLE);
        mRecyclerView.setAdapter(adapter);
    }
}