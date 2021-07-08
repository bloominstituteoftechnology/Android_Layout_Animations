package com.joshuahalvorson.android_fragments_basics;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import io.magicthegathering.javasdk.resource.Card;

public class PhoneDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_detail);

        final DetailsFragment detailFragment = DetailsFragment.newInstance((Card)getIntent().getSerializableExtra("card"));
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, detailFragment).commit();
    }
}
