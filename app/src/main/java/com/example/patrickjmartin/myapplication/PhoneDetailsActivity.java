package com.example.patrickjmartin.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.patrickjmartin.myapplication.PokemonAPI.Pokemon;

public class PhoneDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_details);

        final DetailsFragment detailsFragment = DetailsFragment.
                newInstance((Pokemon)getIntent().getSerializableExtra("pokemon"));
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, detailsFragment)
                .commit();
    }
}
