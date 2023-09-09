package com.example.adoption;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Loading extends AppCompatActivity {

    private ProgressBar progressBar;
    private TextView percentValue;
    ImageView animImg;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        animImg = findViewById(R.id.animImg);
        animImg.setX(0);
        animImg.setY(0);
        progressBar=findViewById(R.id.progressBar);
        percentValue=findViewById(R.id.percentValue);
        progressBar.setMax(100);
        progressBar.setScaleY(5f);
        buffering();

        // new thread for displaying animation
        new Thread(new Runnable() {
            @Override
            public void run() {
                // loop for 45 images
                for(int i=0;i<15;i++)
                {
                    // sleep for 200 mill sec
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    animImg.setX(animImg.getX() + 10);
                    animImg.setY(animImg.getY() + 10);

                }
            }
        }).start();

    }


    public void buffering()
    {
        progressBarAnim buffer = new progressBarAnim(this,progressBar,0,100,percentValue);
        buffer.setDuration(4000);
        progressBar.setAnimation(buffer);

    }
}