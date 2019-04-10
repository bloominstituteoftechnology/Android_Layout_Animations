package com.vivekvishwanath.android_sprint3_challenge;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Context context;
    private EditText pokemonEditText;
    private Button searchButton;

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private PokemonListAdapter pokemonListAdapter;

    private ArrayList<Pokemon> searchedPokemon = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);

        pokemonListAdapter = new PokemonListAdapter(searchedPokemon);
        recyclerView.setAdapter(pokemonListAdapter);

        pokemonEditText = findViewById(R.id.pokemon_edit_text);
        searchButton = findViewById(R.id.search_button);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String nameOrId = pokemonEditText.getText().toString().toLowerCase();
                        Pokemon pokemon = new Pokemon(PokemonDao.getPokemon(nameOrId));
                        boolean containsPokemon = false;
                        if (pokemon.getId() != 0) {
                            for (int i = 0; i < searchedPokemon.size(); i++) {
                                if (pokemon.getId() == searchedPokemon.get(i).getId())
                                    containsPokemon = true;
                            }
                            if (!containsPokemon) {
                                searchedPokemon.add(pokemon);
                                pokemonListAdapter.notifyItemInserted(searchedPokemon.size() - 1);
                            }
                            Intent intent = new Intent(context, ViewPokemon.class);
                            intent.putExtra(Constants.POKEMON_INTENT_KEY, pokemon);
                            startActivity(intent);
                        }
                    }
                }).start();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        recyclerView.removeAllViews();
        pokemonListAdapter = new PokemonListAdapter(searchedPokemon);
        recyclerView.setAdapter(pokemonListAdapter);
    }
}
