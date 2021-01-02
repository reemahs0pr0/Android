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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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
    private TextView message;
    private List<ImageView> images;
    private int imageNo = 0, size;
    private List<String> urls = new ArrayList<String>();
    private DownloadImages myTask;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getBtn = (Button) findViewById(R.id.getBtn);
        message = (TextView) findViewById(R.id.message);
        progressBar =(ProgressBar) findViewById(R.id.progressBar);
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
                if (search.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Nothing was entered!",
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    if (myTask != null) {
                        myTask.cancel(true);
                        imageNo = 0;
                        urls.clear();
                    }
                    for (ImageView image : images) {
                        int id = getResources().getIdentifier("x",
                                "drawable", getPackageName());
                        image.setImageResource(id);
                    }
                    getWebsite(search.getText().toString());
                    String progress = "Downloading " + (imageNo+1) + " of 20 images...";
                    message.setText(progress);
                    progressBar.setProgress(imageNo+1);
                    progressBar.setVisibility(View.VISIBLE);
                    InputMethodManager imm =
                            (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
            }
        });
    }

    private void getWebsite(String search) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                StringBuilder builder = new StringBuilder();
                String url = "https://stocksnap.io/search/" + search;
                try {
                    Document doc = Jsoup.connect(url).get();
                    Elements links = doc.select("img[src]");
                    int i = 0;
                    for (Element link : links) {
                        builder.append(link.attr("src"));
                        if (builder.toString().endsWith("jpg")) {
                            urls.add(builder.toString());
                            i++;
                        }
                        builder = new StringBuilder();
                        if (i == 28) {
                            break;
                        }
                    }
                    for (int j = 0; j < 8; j++) {
                        urls.remove(0);
                    }
                    size = urls.size();
                    startDownloading();
                } catch (IOException e) {
                    builder.append("Error : ").append(e.getMessage()).append("\n");
                }
            }
        }).start();
    }

    private void startDownloading() {
        String url = urls.remove(0);
        myTask = new DownloadImages();
        myTask.execute(url);
    }

    private class DownloadImages extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... urls) {
            Bitmap bitmap = null;
            try {
                InputStream input = new java.net.URL(urls[0]).openStream();
                bitmap = BitmapFactory.decodeStream(input);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            images.get(imageNo).setImageBitmap(result);
            imageNo++;
            if (imageNo <= size-1) {
                String progress = "Downloading " + (imageNo+1) + " of 20 images...";
                message.setText(progress);
                progressBar.setProgress(imageNo+1);
                startDownloading();
            }
            else if (imageNo ==size) {
                message.setText("");
                progressBar.setProgress(imageNo+1);
                progressBar.setVisibility(View.INVISIBLE);
                imageNo = 0;
            }
        }
    }
}