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
    private ImageView image1, image2, image3, image4, image5, image6, image7, image8, image9,
            image10, image11, image12, image13, image14, image15, image16, image17, image18,
            image19, image20;
    private List<ImageView> images;

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
        image7 = (ImageView) findViewById(R.id.image7);
        image8 = (ImageView) findViewById(R.id.image8);
        image9 = (ImageView) findViewById(R.id.image9);
        image10 = (ImageView) findViewById(R.id.image10);
        image11 = (ImageView) findViewById(R.id.image11);
        image12 = (ImageView) findViewById(R.id.image12);
        image13 = (ImageView) findViewById(R.id.image13);
        image14 = (ImageView) findViewById(R.id.image14);
        image15 = (ImageView) findViewById(R.id.image15);
        image16 = (ImageView) findViewById(R.id.image16);
        image17 = (ImageView) findViewById(R.id.image17);
        image18 = (ImageView) findViewById(R.id.image18);
        image19 = (ImageView) findViewById(R.id.image19);
        image20 = (ImageView) findViewById(R.id.image20);
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
            add(image13);
            add(image14);
            add(image15);
            add(image16);
            add(image17);
            add(image18);
            add(image19);
            add(image20);
        }};

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
                String urls[] = new String[20];
                String url = "https://stocksnap.io/search/" + search;

                try {
                    Document doc = Jsoup.connect(url).get();
                    Elements links = doc.select("img[src]");

                    int i = 0;
                    for (Element link : links) {
                        i++;
                        if (i > 3 && i < 24) {
                            builder.append(link.attr("src"));
                            urls[i-4] = builder.toString();
                            builder = new StringBuilder();
                        }
                        if (i == 24) {
                            break;
                        }
                    }
                    new DownloadImages().execute(urls[0], urls[1], urls[2], urls[3], urls[4],
                            urls[5], urls[6], urls[7], urls[8], urls[9], urls[10], urls[11],
                            urls[12], urls[13], urls[14], urls[15], urls[16], urls[17], urls[18],
                            urls[19]);
                } catch (IOException e) {
                    builder.append("Error : ").append(e.getMessage()).append("\n");
                }
            }
        }).start();
    }

    private class DownloadImages extends AsyncTask<String, Void, Bitmap[]> {
        @Override
        protected Bitmap[] doInBackground(String... urls) {
            Bitmap results[] = new Bitmap[20];
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
            int i = 0;
            for (ImageView image : images) {
                image.setImageBitmap(results[i]);
                i++;
            }
        }
    }
}