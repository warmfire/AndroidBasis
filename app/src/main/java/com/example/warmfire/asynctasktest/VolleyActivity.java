package com.example.warmfire.asynctasktest;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by warmfire_2 on 2016/8/19.
 */
public class VolleyActivity extends Activity {

    Button volley_show, volley_back, volley_imgload;
    ImageView volley_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);

        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setClick();
    }

    public void init(){
        volley_show = (Button) findViewById(R.id.volley_show);
        volley_back = (Button) findViewById(R.id.volley_back);
        volley_img = (ImageView) findViewById(R.id.volley_img);
        volley_imgload = (Button) findViewById(R.id.volley_imgload);
    }

    public void setClick(){
        volley_imgload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestQueue mQueue = Volley.newRequestQueue(VolleyActivity.this);

                ImageLoader imageLoader = new ImageLoader(mQueue, new ImageLoader.ImageCache() {
                    @Override
                    public void putBitmap(String url, Bitmap bitmap) {
                    }

                    @Override
                    public Bitmap getBitmap(String url) {
                        return null;
                    }
                });

                ImageLoader.ImageListener listener = ImageLoader.getImageListener(volley_img,
                        R.drawable.cover, R.drawable.cover);

                imageLoader.get("http://images.zhaoonline.com/attachments/clientHome/20151217102535_85.JPG", listener);
            }
        });
        volley_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestQueue mQueue = Volley.newRequestQueue(VolleyActivity.this);

                ImageRequest imageRequest = new ImageRequest(
                        "http://images.zhaoonline.com/attachments/clientHome/20151217102535_85.JPG",
                        new Response.Listener<Bitmap>() {
                            @Override
                            public void onResponse(Bitmap response) {
                                volley_img.setImageBitmap(response);
                            }
                        }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        volley_img.setImageResource(R.drawable.cover);
                    }
                });

                mQueue.add(imageRequest);

            }
        });
        volley_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
