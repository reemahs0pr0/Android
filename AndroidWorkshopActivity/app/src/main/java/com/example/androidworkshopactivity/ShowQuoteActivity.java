package com.example.androidworkshopactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ShowQuoteActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String quote = intent.getStringExtra("quote");

        setContentView(R.layout.activity_showquote);
        TextView textView = findViewById(R.id.quote);
        textView.setText(quote);

        Button btn1 = findViewById(R.id.ok);
        btn1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
