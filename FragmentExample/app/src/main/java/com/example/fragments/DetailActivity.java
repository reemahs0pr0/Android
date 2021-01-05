/*package com.example.fragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity
        implements DetailFragment.IDetailFragment {

    @Override
    public void itemClicked(String content) {
        Toast.makeText(this, content, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        // int itemId = intent.getIntExtra("itemId", 1);
        // Temporarily hard code the itemId value
        int itemId = 2;

        FragmentManager fm = getSupportFragmentManager();
        DetailFragment fragment = (DetailFragment)
                fm.findFragmentById(R.id.fragment_detail);
        fragment.setItemId(itemId);
    }
}*/
