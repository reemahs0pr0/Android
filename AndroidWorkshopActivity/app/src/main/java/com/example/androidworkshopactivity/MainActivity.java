package com.example.androidworkshopactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {

    private final int REQ_RESPONSE = 1;
    String quote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(this);

        Button btn2 = findViewById(R.id.btn2);
        btn2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn1) {
            Intent intent = new Intent(this, WriteQuoteActivity.class);
            startActivityForResult(intent, REQ_RESPONSE);
        }
        else if (id == R.id.btn2) {
            Intent intent = new Intent(this, ShowQuoteActivity.class);
            intent.putExtra("quote", quote);
            startActivity(intent);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == REQ_RESPONSE) {
            if (resultCode == RESULT_OK) {
                quote = intent.getStringExtra("quote");
            }
        }
    }
}