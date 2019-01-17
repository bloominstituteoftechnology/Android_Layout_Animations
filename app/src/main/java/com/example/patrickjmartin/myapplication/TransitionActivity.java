package com.example.patrickjmartin.myapplication;

import android.content.Intent;
import android.graphics.ImageDecoder;
import android.graphics.drawable.AnimatedImageDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.patrickjmartin.myapplication.PokemonAPI.Pokemon;

import java.io.IOException;

public class TransitionActivity extends AppCompatActivity {

    private ImageView gifImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition);

        gifImageView = findViewById(R.id.transition_imageView);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    final Drawable drawable = ImageDecoder.
                            decodeDrawable(ImageDecoder
                                    .createSource(getResources(), R.drawable.suprised_pikachu));

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            gifImageView.setImageDrawable(drawable);
                        }
                    });
                    if(drawable instanceof AnimatedImageDrawable) {
                        ((AnimatedImageDrawable) drawable).start();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

}
