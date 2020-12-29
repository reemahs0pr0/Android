package com.example.androidworkshoppermissions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final int REQ_RECORD_AUDIO = 1;
    private final int REQ_RECEIVE_SMS = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button record = findViewById(R.id.recordaudio);
        record.setOnClickListener(this);

        Button receive = findViewById(R.id.receivesms);
        receive.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.recordaudio) {
            String[] permissions = {Manifest.permission.RECORD_AUDIO};

            if (checkSelfPermission(permissions[0]) == PackageManager.PERMISSION_GRANTED) {
                Toast msg = Toast.makeText(this,
                        "Permission to record audio already granted", Toast.LENGTH_SHORT);
                msg.show();
            }
            else {
                ActivityCompat.requestPermissions(this, permissions, REQ_RECORD_AUDIO);
            }
        }
        else if (id == R.id.receivesms) {
            String[] permissions = {Manifest.permission.RECEIVE_SMS};

            if (checkSelfPermission(permissions[0]) == PackageManager.PERMISSION_GRANTED) {
                Toast msg = Toast.makeText(this,
                        "Permission to receive sms already granted", Toast.LENGTH_SHORT);
                msg.show();
            }
            else {
                ActivityCompat.requestPermissions(this, permissions, REQ_RECEIVE_SMS);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQ_RECORD_AUDIO) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast msg = Toast.makeText(this,
                        "Granted permission to record audio", Toast.LENGTH_SHORT);
                msg.show();
            }
            else {
                Toast msg = Toast.makeText(this,
                        "Denied permission to record audio", Toast.LENGTH_SHORT);
                msg.show();
            }
        }
        else if (requestCode == REQ_RECEIVE_SMS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast msg = Toast.makeText(this,
                        "Granted permission to receive sms", Toast.LENGTH_SHORT);
                msg.show();
            }
            else {
                Toast msg = Toast.makeText(this,
                        "Denied permission to receive sms", Toast.LENGTH_SHORT);
                msg.show();
            }
        }
    }
}