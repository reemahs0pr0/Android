package com.example.androidworkshopquotesonboot;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.util.Date;
import java.util.Random;

public class MyReceiver extends BroadcastReceiver {

    private String quotes[] = new String[] {
            "Life is the sum of our choices",
            "What hurts more – the pain of hard work or the pain of regret?",
            "Let your life be shaped by decisions you made, not by the ones you didn’t",
            "Knowledge is knowing what to say. Wisdom is knowing when to say it",
            "Critique to sharpen; not to put down",
            "Our greatest fear should not be of failure, " +
                    "but of succeeding at things that don’t really matter",
            "Creativity comes from constraint",
            "Change begins, when you start trying"
    };

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action == null)
            return;
        /*if (action.equals(Intent.ACTION_BOOT_COMPLETED)) {
            Random rnd = new Random();
            Toast.makeText(context, quotes[rnd.nextInt(quotes.length)], Toast.LENGTH_LONG).show();
        }*/
        if (action.equals("ACTION_1")) {
            Random rnd = new Random();
            Toast.makeText(context, quotes[rnd.nextInt(quotes.length)], Toast.LENGTH_LONG).show();

            Date when = new Date(System.currentTimeMillis());
            Intent intent2 = new Intent(context, MyReceiver.class);
            intent2.setAction("ACTION_1");
            PendingIntent pendingIntent = PendingIntent.getBroadcast(
                    context, 0, intent2,
                    PendingIntent.FLAG_UPDATE_CURRENT);
            AlarmManager alarm = (AlarmManager) context
                    .getSystemService(Context.ALARM_SERVICE);
            alarm.setExact(AlarmManager.RTC_WAKEUP, when.getTime() + 5000,
                    pendingIntent) ;
        }
    }
}