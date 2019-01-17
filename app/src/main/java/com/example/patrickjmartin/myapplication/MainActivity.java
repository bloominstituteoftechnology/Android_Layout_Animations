package com.example.patrickjmartin.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.patrickjmartin.myapplication.PokemonAPI.Pokemon;


public class MainActivity extends AppCompatActivity implements PokemonFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PokemonFragment fragment = new PokemonFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_holder, fragment).commit();

        findViewById(R.id.transition_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent transitionIntent = new Intent(getApplicationContext(), TransitionActivity.class);
                startActivity(transitionIntent);
            }
        });

    }

    @Override
    public void onListFragmentInteraction(Pokemon item) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("pokemon", item);
        Intent intent = new Intent(getApplicationContext(), PhoneDetailsActivity.class);
        intent.putExtra("pokemon", item);
        startActivity(intent);
    }
}
