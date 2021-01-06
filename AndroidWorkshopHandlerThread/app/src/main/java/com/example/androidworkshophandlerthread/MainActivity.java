package com.example.androidworkshophandlerthread;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button computeBtn;
    private EditText upTo;
    private Handler hdl, hdl2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        computeBtn = (Button) findViewById(R.id.computeBtn);
        upTo = (EditText) findViewById(R.id.upTo);

        HandlerThread ht = new HandlerThread("bgThread");
        ht.start();
        hdl = new Handler(ht.getLooper()) {
            public void handleMessage(@NonNull Message msg) {
                int prev = 1, current = 1, temp;
                String txt = "1 1";
                while (true) {
                    temp = prev;
                    prev = current;
                    current += temp;
                    if (current > (Integer) msg.obj)
                        break;
                    txt += " " + String.valueOf(current);
                }
                Message newMsg = new Message();
                newMsg.obj = txt;
                hdl2.sendMessage(newMsg);
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();

        computeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int max = Integer.parseInt(upTo.getText().toString());
                Message msg = new Message();
                msg.obj = max;
                hdl.sendMessage(msg);
            }
        });

        hdl2 = new Handler(getMainLooper()) {
            public void handleMessage(@NonNull Message msg) {
                System.out.println(msg.obj.toString());
                Toast.makeText(getApplicationContext(), msg.obj.toString(), Toast.LENGTH_SHORT)
                        .show();
            }
        };
    }
}