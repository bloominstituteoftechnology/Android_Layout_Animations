package com.jakeesveld.android_layoutanimations;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE = 15;
    public static final String PREFS_ID_LIST_KEY = "idList";
    EditText editSearch;
    Button buttonSubmit;
    Context context;
    static ArrayList<Pokemon> pokemonList;
    PokemonListAdapter listAdapter;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prefs = getSharedPreferences("Pokemon", MODE_PRIVATE);
        editSearch = findViewById(R.id.edit_search);
        buttonSubmit = findViewById(R.id.button_search);
        final PokemonDAO dao = new PokemonDAO();
        context = this;
        if (prefs.getString(PREFS_ID_LIST_KEY, null) != null) {
            pokemonList = new ArrayList<>();
            Thread prefsGet = new Thread(new Runnable() {
                @Override
                public void run() {
                    String idListString = prefs.getString(PREFS_ID_LIST_KEY, null);
                    String[] idList = idListString.split(",");
                    for (int i = 0; i < idList.length; i++) {
                        String pokemonId = idList[i];
                        Pokemon listPokemon = dao.getPokemonById(pokemonId);
                        final Pokemon listPokemonWithSprite = new Pokemon(
                                listPokemon.getName(),
                                listPokemon.getSpriteUrl(),
                                listPokemon.getId(),
                                listPokemon.getAbilities(),
                                listPokemon.getTypes(),
                                NetworkAdapter.bitmapFromUrl(listPokemon.getSpriteUrl()));

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                pokemonList.add(listPokemonWithSprite);
                            }
                        });
                    }
                }
            });
            prefsGet.start();
            try {
                prefsGet.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else {
            pokemonList = new ArrayList<>();
        }
        listAdapter = new PokemonListAdapter(pokemonList);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setAdapter(listAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);



        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String searchQuery = editSearch.getText().toString();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final Pokemon searchPokemon = dao.getPokemonById(searchQuery);

                        Intent intent = new Intent(context, ViewerActivity.class);
                        intent.putExtra(Pokemon.POKEMON_INTENT_KEY, searchPokemon);
                        startActivityForResult(intent, REQUEST_CODE);
                    }
                }).start();
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        listAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(final int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            if (data != null) {
                final Pokemon returnedPokemon = (Pokemon) data.getSerializableExtra(Pokemon.POKEMON_INTENT_KEY);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final Pokemon returnedPokemonWithSprite = new Pokemon(returnedPokemon.getName(),
                                returnedPokemon.getSpriteUrl(),
                                returnedPokemon.getId(),
                                returnedPokemon.getAbilities(),
                                returnedPokemon.getTypes(),
                                NetworkAdapter.bitmapFromUrl(returnedPokemon.getSpriteUrl()));

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                pokemonList.add(returnedPokemonWithSprite);
                                listAdapter.notifyDataSetChanged();
                            }
                        });
                    }
                }).start();

            }

        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < pokemonList.size(); i++) {
            Pokemon selected = pokemonList.get(i);
            builder.append(selected.getId());
            builder.append(",");
        }
        String idList = builder.toString();
        SharedPreferences.Editor edit = prefs.edit();
        edit.putString(PREFS_ID_LIST_KEY, idList);
        edit.apply();
    }
}
