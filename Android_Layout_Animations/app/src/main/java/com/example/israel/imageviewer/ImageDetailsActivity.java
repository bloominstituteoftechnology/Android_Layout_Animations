package com.example.israel.imageviewer;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageDetailsActivity extends AppCompatActivity {

    public static final String DEBUG_TAG = MainActivity.DEBUG_TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        getWindow().setEnterTransition(new Explode());
        getWindow().setExitTransition(new Explode());

        setContentView(R.layout.activity_image_details);

        Log.d(DEBUG_TAG, "ImageDetailsActivity.onCreate");

        ImageView imageView = findViewById(R.id.imageView);
        final Button fullscreenButton = findViewById(R.id.button_fullscreen); // this is a clickable image
        TextView nameTextView = findViewById(R.id.textView_name);
        TextView filePathTextView = findViewById(R.id.textView_file_path);

        // retrieve image data from intent
        Intent intent = getIntent();
        final ImageData imageData = (ImageData)intent.getSerializableExtra("image data");

        // set image
        Uri uri = imageData.getUri();
        imageView.setImageURI(uri);

        // set name
        String nameStr = "Name: ";
        nameStr += imageData.getName();
        nameTextView.setText(nameStr);

        // set path
        String filePathStr = "File Path: ";
        filePathStr += imageData.getFilePath();
        filePathTextView.setText(filePathStr);

        // bind create full screen activity
        fullscreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createFullscreenActivity(imageData.getUriStr());
            }
        });
    }

    private void createFullscreenActivity(String uriStr) {
        Intent intent = new Intent(this, FullscreenActivity.class);
        intent.putExtra("uri string", uriStr);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        Log.d(DEBUG_TAG, "ImageDetailsActivity.onStart");

        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d(DEBUG_TAG, "ImageDetailsActivity.onResume");

        super.onResume();
    }

    // looper loops here

    @Override
    protected void onPause() {
        Log.d(DEBUG_TAG, "ImageDetailsActivity.onPause");

        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d(DEBUG_TAG, "ImageDetailsActivity.onStop");

        super.onStop();
    }

    @Override
    protected void onRestart() {
        Log.d(DEBUG_TAG, "ImageDetailsActivity.onRestart");

        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        Log.d(DEBUG_TAG, "ImageDetailsActivity.onDestroy");

        super.onDestroy();
    }
}
