package com.example.fetchimage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class MainActivity2 extends AppCompatActivity {

    private Boolean running = true;
    private int seconds = 0;
    private ImageView image1, image2, image3, image4, image5, image6, image7, image8, image9,
            image10, image11, image12;
    private TextView matches;
    private List<ImageView> images;
    private List<Bitmap> clickedImagesBitmaps = new ArrayList<Bitmap>();
    private List<String> clickedImagesBitmapStrings = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2_main);

        matches = (TextView) findViewById(R.id.matches);
        image1 = (ImageView) findViewById(R.id.image1);
        image2 = (ImageView) findViewById(R.id.image2);
        image3 = (ImageView) findViewById(R.id.image3);
        image4 = (ImageView) findViewById(R.id.image4);
        image5 = (ImageView) findViewById(R.id.image5);
        image6 = (ImageView) findViewById(R.id.image6);
        image7 = (ImageView) findViewById(R.id.image7);
        image8 = (ImageView) findViewById(R.id.image8);
        image9 = (ImageView) findViewById(R.id.image9);
        image10 = (ImageView) findViewById(R.id.image10);
        image11 = (ImageView) findViewById(R.id.image11);
        image12 = (ImageView) findViewById(R.id.image12);
        images = new ArrayList<ImageView>() {{
            add(image1);
            add(image2);
            add(image3);
            add(image4);
            add(image5);
            add(image6);
            add(image7);
            add(image8);
            add(image9);
            add(image10);
            add(image11);
            add(image12);
        }};

        Intent intent = getIntent();
        SharedPreferences pref = getSharedPreferences("clickedImages", Context.MODE_PRIVATE);
        clickedImagesBitmapStrings.add(pref.getString("image1", null));
        clickedImagesBitmapStrings.add(pref.getString("image2", null));
        clickedImagesBitmapStrings.add(pref.getString("image3", null));
        clickedImagesBitmapStrings.add(pref.getString("image4", null));
        clickedImagesBitmapStrings.add(pref.getString("image5", null));
        clickedImagesBitmapStrings.add(pref.getString("image6", null));

        for (int i = 0; i < 6; i++) {
            clickedImagesBitmapStrings.add(clickedImagesBitmapStrings.get(i));
        }
        Collections.shuffle(clickedImagesBitmapStrings);

        int j = 0;
        for (ImageView image : images) {
            image.setImageBitmap(StringToBitMap(clickedImagesBitmapStrings.get(j)));
            j++;
        }

        runTimer();
    }

    private void runTimer() {
        final TextView timeView = (TextView)findViewById(R.id.timer);
        final Handler handler  = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;

                String time = String.format(Locale.getDefault(),"%d:%02d:%02d", hours,
                        minutes, secs);
                timeView.setText(time);
                if (running) {
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }

    public Bitmap StringToBitMap(String encodedString){
        try {
            byte [] encodeByte= Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap=BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch(Exception e) {
            e.getMessage();
            return null;
        }
    }
}
