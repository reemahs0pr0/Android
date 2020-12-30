package com.example.files;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.files.R;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    EditText mInputTxt;
    Button mSaveBtn;
    Button mReadBtn;

    File mTargetFile;

    String filePath = "SampleFolder";
    String fileName = "SampleFile.txt";

    private static final int REQUEST_CODE_WRITE_EXTERNAL_STORAGE_PERMISSION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInputTxt = (EditText) findViewById(R.id.inputTxt);

        mSaveBtn = (Button) findViewById(R.id.btnSave);
        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int writeExtStrgPer = ContextCompat.checkSelfPermission(
                        MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

                if (writeExtStrgPer == PackageManager.PERMISSION_GRANTED) {
                    File publicPath = Environment.getExternalStorageDirectory();
                    mTargetFile = new File(publicPath, filePath + "/" + fileName);
                    Toast.makeText(getApplicationContext(),
                            "Permission to write on public external storage already granted. "
                                    + "Writing on public external storage.",
                            Toast.LENGTH_SHORT).show();
                    writeToFile();
                } else {
                    String[] permissions = new String[]{
                            Manifest.permission.WRITE_EXTERNAL_STORAGE};
                    ActivityCompat.requestPermissions(
                            MainActivity.this, permissions,
                            REQUEST_CODE_WRITE_EXTERNAL_STORAGE_PERMISSION);
                }
            }
        });

        mReadBtn = (Button) findViewById(R.id.btnRead);
        mReadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int writeExtStrgPer = ContextCompat.checkSelfPermission(
                        MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

                if (writeExtStrgPer == PackageManager.PERMISSION_GRANTED) {
                    File publicPath = Environment.getExternalStorageDirectory();
                    mTargetFile = new File(publicPath, filePath + "/" + fileName);
                    Toast.makeText(getApplicationContext(),
                            "Reading from public external storage.",
                            Toast.LENGTH_SHORT).show();
                }
                else if (isExternalStorageAvailable()) {
                    mTargetFile = new File(
                            getExternalFilesDir(null), filePath + "/" + fileName);
                    Toast.makeText(getApplicationContext(),
                            "Reading from App-specific external storage.",
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    mTargetFile = new File(getFilesDir(), filePath + "/" + fileName);
                    Toast.makeText(getApplicationContext(),
                            "App-specific external storage unavailable. " +
                                    "Reading from App-specific internal storage.",
                            Toast.LENGTH_SHORT).show();
                }
                readFromFile();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CODE_WRITE_EXTERNAL_STORAGE_PERMISSION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this,
                        "Granted permission to write on public external storage. " +
                                "Writing on public external storage.", Toast.LENGTH_SHORT).show();
                File publicPath = Environment.getExternalStorageDirectory();
                mTargetFile = new File(publicPath, filePath + "/" + fileName);
            }
            else {
                Toast.makeText(this,
                        "Denied permission to write on public external storage.",
                        Toast.LENGTH_SHORT).show();
                if (isExternalStorageAvailable()) {
                    mTargetFile = new File(
                            getExternalFilesDir(null), filePath + "/" + fileName);
                    Toast.makeText(getApplicationContext(),
                            "Writing on App-specific external storage.",
                            Toast.LENGTH_SHORT).show();
                } else {
                    mTargetFile = new File(getFilesDir(), filePath + "/" + fileName);
                    Toast.makeText(getApplicationContext(),
                            "App-specific external storage unavailable. " +
                                    "Writing on App-specific internal storage.",
                            Toast.LENGTH_SHORT).show();
                }
            }
            writeToFile();
        }
    }

    protected void writeToFile() {
        try {
            // Make sure that the parent folder exists
            File parent = mTargetFile.getParentFile();
            if (!parent.exists() && !parent.mkdirs()) {
                throw new IllegalStateException("Couldn't create dir: " + parent);
            }

            // Write to file
            FileOutputStream fos = new FileOutputStream(mTargetFile);
            fos.write(mInputTxt.getText().toString().getBytes());
            fos.close();

            mInputTxt.setText("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void readFromFile() {
        String data = "";
        try {
            FileInputStream fis = new FileInputStream(mTargetFile);
            DataInputStream in = new DataInputStream(fis);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            while ((strLine = br.readLine()) != null) {
                data = data + strLine;
            }
            in.close();

            mInputTxt.setText(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean isExternalStorageAvailable() {
        String extStorageState = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(extStorageState);
    }
}
