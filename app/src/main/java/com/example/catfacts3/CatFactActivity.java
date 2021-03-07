package com.example.catfacts3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CatFactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_fact);
        TextView catFactText = (TextView) findViewById(R.id.tv_cat_fact_activity);
        TextView catFactAuthor = (TextView) findViewById(R.id.tv_cat_fact_author);
        Button backButton = (Button) findViewById(R.id.btn_go_back);
        Intent intent = getIntent();
        String catFact = intent.getStringExtra("fact");
        String author = intent.getStringExtra("author");
        catFactText.setText(catFact);
        catFactAuthor.setText(getString(R.string.author_msg, author));
        backButton.setOnClickListener(v -> finish());
    }
}