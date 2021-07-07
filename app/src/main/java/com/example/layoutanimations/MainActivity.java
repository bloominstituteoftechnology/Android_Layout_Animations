package com.example.layoutanimations;


import android.app.Activity;
import android.app.ActivityOptions;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionValues;
import android.view.Window;

public class MainActivity extends AppCompatActivity implements PokemonFragment.OnListFragmentInteractionListener, DetailsFragment.OnFragmentInteractionListener {

    public static final String POKEMON_DETAILS_KEY = "pokemon_details_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        final Fade transition = new Fade();
        transition.setDuration(250);
        transition.setStartDelay(250);
        getWindow().setEnterTransition(transition);
        getWindow().setExitTransition(transition);
        supportPostponeEnterTransition();

        setContentView(R.layout.activity_main);

        new Thread(new Runnable() {
            @Override
            public void run() {

                PokemonDao.setAllPokemon(PokemonDao.getPokemonURLS());
                for (int i = 1; i < PokemonDao.getAllPokemon().size(); i++) {

                }
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, new PokemonFragment()).commit();
            }
        }).start();


    }

/*
    Bundle arguments = new Bundle();
    // S04M03-18 update this to pass out object along
            arguments.putSerializable(ItemDetailFragment.ARG_ITEM_ID, getIntent().getSerializableExtra(ItemDetailFragment.ARG_ITEM_ID));
    *//*arguments.putString(ItemDetailFragment.ARG_ITEM_ID,
                        getIntent().getStringExtra(ItemDetailFragment.ARG_ITEM_ID));*//*
    ItemDetailFragment fragment = new ItemDetailFragment();
            fragment.setArguments(arguments);
    getSupportFragmentManager().beginTransaction()
                                       .add(R.id.item_detail_container, fragment)
                                       .commit();*/

    @Override
    public void onListFragmentInteraction(Pokemon pokemon) {
        DetailsFragment detailsFragment = new DetailsFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable(POKEMON_DETAILS_KEY, pokemon);
        detailsFragment.setArguments(bundle);

        if (getResources().getBoolean(R.bool.is_tablet)) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_details, detailsFragment)
                    .addToBackStack(null)
                    .commit();
        } else {
            getSupportFragmentManager().beginTransaction()
                    .setTransition(android.R.transition.explode)
                    .addToBackStack(null)
                    .replace(R.id.fragment_container, detailsFragment)
                    .commit();
        }
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
