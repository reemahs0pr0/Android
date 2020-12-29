package com.example.androidworkshopimplicitintents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button visit = findViewById(R.id.visitnus);
        visit.setOnClickListener(this);

        Button locate = findViewById(R.id.locatenus);
        locate.setOnClickListener(this);

        Button call = findViewById(R.id.callnus);
        call.setOnClickListener(this);

        Button email = findViewById(R.id.emailnus);
        email.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.visitnus) {
            Uri uri = Uri.parse("https://www.nus.edu.sg");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            if (intent.resolveActivity(getPackageManager()) != null)
                startActivity(intent);
        }
        else if (id == R.id.locatenus) {
            Uri uri = Uri.parse("geo:1.296648,103.7742052");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            if (intent.resolveActivity(getPackageManager()) != null)
                startActivity(intent);
        }
        else if (id == R.id.callnus) {
            Uri uri = Uri.parse("tel:(+65)65166666");
            Intent intent = new Intent(Intent.ACTION_DIAL, uri);
            if (intent.resolveActivity(getPackageManager()) != null)
                startActivity(intent);

        }
        else if (id == R.id.emailnus) {
            Uri uri = Uri.parse("mailto:null@nus.edu.sg");
            Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
            intent.putExtra(Intent.EXTRA_SUBJECT, "For Enquiry");
            intent.putExtra(Intent.EXTRA_TEXT, "Please state your enquiry...");
            if (intent.resolveActivity(getPackageManager()) != null)
                startActivity(intent);
        }
    }
}