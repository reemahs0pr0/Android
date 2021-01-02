package com.example.fetchimage;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2_main);

        Intent intent = getIntent();
        ArrayList<String> clickedUrls = intent.getStringArrayListExtra("clickedurls");
    }
}
