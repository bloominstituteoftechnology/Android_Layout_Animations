package com.example.lambda_school_loaner_47.contactsapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.concurrent.atomic.AtomicBoolean;

public class DetailsActivity extends AppCompatActivity {

    TextView fullName;
    TextView email;
    TextView txtphone;
    ImageView image;
    Bitmap bitmap = null;
    AtomicBoolean canceled = new AtomicBoolean(false);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Contacts contacts = getIntent().getParcelableExtra(ContactsAdapter.CONTACT_ADAPTER);

        fullName = findViewById(R.id.tvFullName);
        email    = findViewById(R.id.tvEmail);
        txtphone = findViewById(R.id.tvPhone);
        image    = findViewById(R.id.ivLarge);

        new Thread(new Runnable() {
            @Override
            public void run() {
                bitmap = ContactsDao.getImage(contacts.getLargeImage(),canceled);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        image.setImageBitmap(bitmap);

                    }
                });

            }
        }).start();

        fullName.setText(contacts.getFullName());
        email.setText(contacts.getEmail());
        txtphone.setText(contacts.getPhone());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
