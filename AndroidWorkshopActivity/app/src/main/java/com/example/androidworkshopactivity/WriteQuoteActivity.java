package com.example.androidworkshopactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class WriteQuoteActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writequote);

        Button btn1 = findViewById(R.id.ok);
        btn1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        EditText quote = findViewById(R.id.quote);
        Intent response = new Intent();
        response.putExtra("quote", quote.getText().toString());
        setResult(RESULT_OK, response);
        finish();
    }
}
