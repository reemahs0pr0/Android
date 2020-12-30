package com.example.sharedpreferences_emergencycontact;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText mInputName;
    EditText mInputPhoneNumber;
    Button mSaveBtn;
    Button mRetrieveBtn;
    Button mClearBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInputName = (EditText) findViewById(R.id.name);
        mInputPhoneNumber = (EditText) findViewById(R.id.phoneNumber);
        mSaveBtn = (Button) findViewById(R.id.btnSave);
        mRetrieveBtn = (Button) findViewById(R.id.btnRetrieve);
        mClearBtn = (Button) findViewById(R.id.btnClear);

        SharedPreferences pref =
                getSharedPreferences("emergency_contact", MODE_PRIVATE);

        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mInputName.getText().toString();
                String phoneNumber = mInputPhoneNumber.getText().toString();
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("name", name);
                editor.putString("phoneNumber", phoneNumber);
                editor.commit();
                Toast.makeText(getApplicationContext(),
                        "Emergency contact saved", Toast.LENGTH_SHORT).show();
                mInputName.setText("");
                mInputPhoneNumber.setText("");
            }
        });

        mRetrieveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = pref.getString("name", "");
                String phoneNumber = pref.getString("phoneNumber", "");
                mInputName.setText(name);
                mInputPhoneNumber.setText(phoneNumber);
            }
        });

        mClearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = pref.edit();
                editor.clear();
                editor.commit();
                Toast.makeText(getApplicationContext(),
                        "Emergency contact cleared", Toast.LENGTH_SHORT).show();
            }
        });
    }
}