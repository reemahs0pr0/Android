package com.example.fetchimage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button getBtn;
    private List<String> urls = new ArrayList<>();
    private ImageView image1, image2, image3, image4, image5, image6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getBtn = (Button) findViewById(R.id.getBtn);
        image1 = (ImageView) findViewById(R.id.image1);
        image2 = (ImageView) findViewById(R.id.image2);
        image3 = (ImageView) findViewById(R.id.image3);
        image4 = (ImageView) findViewById(R.id.image4);
        image5 = (ImageView) findViewById(R.id.image5);
        image6 = (ImageView) findViewById(R.id.image6);
        getBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText search = findViewById(R.id.search);
                getWebsite(search.getText().toString());
                InputMethodManager imm =
                        (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        });
    }

    private void getWebsite(String search) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                StringBuilder builder = new StringBuilder();
                String urls[] = new String[6];
                String url = "https://stocksnap.io/search/" + search;

                try {
                    Document doc = Jsoup.connect(url).get();
                    Elements links = doc.select("img[src]");

                    int i = 0;
                    for (Element link : links) {
                        i++;
                        if (i > 3 && i < 10) {
                            builder.append(link.attr("src"));
                            urls[i-4] = builder.toString();
                            builder = new StringBuilder();
                        }
                        if (i == 10) {
                            break;
                        }
                    }
                    new DownloadImages().execute(urls[0], urls[1], urls[2], urls[3], urls[4],
                            urls[5]);
                } catch (IOException e) {
                    builder.append("Error : ").append(e.getMessage()).append("\n");
                }
            }
        }).start();
    }

    private class DownloadImages extends AsyncTask<String, Void, Bitmap[]> {
        Bitmap urlsBitmap[] = new Bitmap[5];

        @Override
        protected Bitmap[] doInBackground(String... urls) {
            Bitmap results[] = new Bitmap[6];
            int i = 0;
            for (String url : urls) {
                try {
                    InputStream input = new java.net.URL(url).openStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(input);
                    results[i] = bitmap;
                    i++;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return results;
        }

        @Override
        protected void onPostExecute(Bitmap[] results) {
            image1.setImageBitmap(results[0]);
            image2.setImageBitmap(results[1]);
            image3.setImageBitmap(results[2]);
            image4.setImageBitmap(results[3]);
            image5.setImageBitmap(results[4]);
            image6.setImageBitmap(results[5]);
        }
    }
}