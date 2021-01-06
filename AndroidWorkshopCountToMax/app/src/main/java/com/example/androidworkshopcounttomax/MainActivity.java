package com.example.androidworkshopcounttomax;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button btn;
    private EditText count;
    private Thread bkgdThread;
    private boolean counting = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.btn);
        count = (EditText) findViewById(R.id.count);

        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (counting == true) {
                    bkgdThread.interrupt();
                    count.setText("");
                    btn.setText("START");
                    counting = false;
                }
                else {
                    System.out.println(bkgdThread);
                    count.setText("Computing...");
                    btn.setText("STOP");
                    bkgdThread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            counting = true;
                            int i = 1;
                            while (i < Integer.MAX_VALUE) {
                                if (Thread.interrupted()) {
                                    return;
                                }
                                i++;
                            }
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    count.setText(Integer.toString(Integer.MAX_VALUE));
                                    btn.setText("START");
                                }
                            });
                            counting = false;
                        }
                    });
                    bkgdThread.start();
                }
            }
        });
    }
}