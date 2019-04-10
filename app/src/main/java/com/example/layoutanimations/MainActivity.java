package com.example.layoutanimations;


import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements PokemonFragment.OnListFragmentInteractionListener, DetailsFragment.OnFragmentInteractionListener {

    public static final String POKEMON_DETAILS_KEY = "pokemon_details_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Thread(new Runnable() {
            @Override
            public void run() {

                PokemonDao.setAllPokemon(PokemonDao.getPokemonURLS());
                for (int i = 1; i < 250; i++) {

                }
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, new PokemonFragment()).commit();
            }
        }).start();


    }


    @Override
    public void onListFragmentInteraction(Pokemon pokemon) {
        DetailsFragment detailsFragment = new DetailsFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable(POKEMON_DETAILS_KEY, pokemon);
        detailsFragment.setArguments(bundle);

        if (getResources().getBoolean(R.bool.is_tablet)) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_details, detailsFragment)
                    .addToBackStack(null)
                    .commit();
        } else {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, detailsFragment)
                    .addToBackStack(null)
                    .commit();
        }
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
