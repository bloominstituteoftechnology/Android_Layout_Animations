package com.rybarstudios.imageviewer;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;

public class DetailsActivity extends AppCompatActivity implements Serializable {

    private Context context;
    private ImageView imageView;
    private ImageData mImageData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        getWindow().setEnterTransition(new Explode());
        getWindow().setExitTransition(new Explode());

        setContentView(R.layout.activity_details);
        Log.i("ActivityLifecycle", getLocalClassName() + " - onCreate");

        context = this;

        imageView = findViewById(R.id.view_image);

        Intent imageIntent = getIntent();
        mImageData = (ImageData) imageIntent.getSerializableExtra(Intent.EXTRA_STREAM);
        imageView.setImageURI(mImageData.getUri());

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FullscreenActivity.class);
                intent.putExtra(Intent.EXTRA_STREAM, mImageData.getUri().toString());
                startActivity(intent);
            }
        });

        ((TextView)findViewById(R.id.view_image_name)).setText(mImageData.getName());

        ((TextView)findViewById(R.id.view_image_uri)).setText(mImageData.getUri().toString());

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("ActivityLifecycle", getLocalClassName() + " - onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("ActivityLifecycle", getLocalClassName() + " - onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("ActivityLifecycle", getLocalClassName() + " - onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("ActivityLifecycle", getLocalClassName() + " - onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("ActivityLifecycle", getLocalClassName() + " - onDestroy");
    }

}
