package com.example.androidworkshopquotesonboot;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private Button start, stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start = (Button) findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date when = new Date(System.currentTimeMillis());
                Intent intent = new Intent(getApplicationContext(), MyReceiver.class);
                intent.setAction("ACTION_1");
                        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                                getApplicationContext(), 0, intent,
                                PendingIntent.FLAG_UPDATE_CURRENT);
                AlarmManager alarm = (AlarmManager) getApplicationContext()
                        .getSystemService(Context.ALARM_SERVICE);
                alarm.setExact(AlarmManager.RTC_WAKEUP, 0,
                        pendingIntent) ;
            }
        });
        stop = (Button) findViewById(R.id.stop);
        stop.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MyReceiver.class);
                intent.setAction("ACTION_1");
                        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                                getApplicationContext(), 0, intent,
                                PendingIntent.FLAG_UPDATE_CURRENT);
                AlarmManager alarm =
                        (AlarmManager) getApplicationContext()
                                .getSystemService(Context.ALARM_SERVICE);
                alarm.cancel(pendingIntent);
            }
        });

    }
}