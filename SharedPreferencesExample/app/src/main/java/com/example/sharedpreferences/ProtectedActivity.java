package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.sharedpreferences.R;

public class ProtectedActivity extends AppCompatActivity {
    TextView mInfoTxt;
    Button mLogoutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_protected);

        SharedPreferences pref = getSharedPreferences("user_credentials", MODE_PRIVATE);
        String username = pref.getString("username","");

        mInfoTxt = (TextView) findViewById(R.id.txtInfo);
        String text = "Hi, " + username + "! All the best in the next exam!";
        mInfoTxt.setText(text);

        mLogoutBtn = (Button) findViewById(R.id.btnLogout);

        mLogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = pref.edit();
                editor.clear();
                editor.commit();

                finish();
            }
        });
    }
}
