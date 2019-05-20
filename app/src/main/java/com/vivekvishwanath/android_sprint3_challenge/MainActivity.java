package com.vivekvishwanath.android_sprint3_challenge;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private Context context;
    private EditText pokemonEditText;
    private Button searchButton;

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private PokemonListAdapter pokemonListAdapter;

    private ArrayList<Pokemon> searchedPokemon = new ArrayList<>();
    public static HashMap<Integer, Bitmap> sprites = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        recyclerView = findViewById(R.id.recycler_view);
        pokemonEditText = findViewById(R.id.pokemon_edit_text);
        searchButton = findViewById(R.id.search_button);
        recyclerView.setHasFixedSize(true);

    }

    @Override
    protected void onResume() {
        super.onResume();
        recyclerView.removeAllViews();

        linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);

        pokemonListAdapter = new PokemonListAdapter(searchedPokemon);
        recyclerView.setAdapter(pokemonListAdapter);

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
                                sprites.put(pokemon.getId(), PokemonDao.getPokemonSprite(pokemon.getSpriteUrl()));
                                searchedPokemon.add(pokemon);
                                pokemonListAdapter.notifyItemInserted(searchedPokemon.size() - 1);
                            }
                            Intent intent = new Intent(context, ViewPokemon.class);
                            intent.putExtra(Constants.POKEMON_INTENT_KEY, pokemon);
                            startActivity(intent);
                            sprites.put(pokemon.getId(), PokemonDao.getPokemonSprite(pokemon.getSpriteUrl()));
                        }
                    }
                }).start();
            }
        });
    }
}
