package com.vivekvishwanath.android_sprint3_challenge;

import android.graphics.Bitmap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PokemonDao {

    private static final String BASE_URL = "https://pokeapi.co/api/v2/pokemon/";

    public static ArrayList<Pokemon> getAllPokemon() {
        ArrayList<Pokemon> pokemonList = new ArrayList<>();
        String result = NetworkAdapter.httpGETRequest(BASE_URL);
        try {
            JSONObject allPokemonJson = new JSONObject(result);
            JSONArray pokemonJsonArray = allPokemonJson.getJSONArray("results");
            for (int i = 0; i < pokemonJsonArray.length(); i++) {
                Pokemon pokemon = new Pokemon(getPokemon(pokemonJsonArray.getJSONObject(i).getString("name")));
                pokemonList.add(pokemon);
            }
            return pokemonList;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return pokemonList;
    }

    public static JSONObject getPokemon(String nameOrId) {
        JSONObject pokemonJSON = null;
        String pokemonUrl = BASE_URL + nameOrId;
        String result = NetworkAdapter.httpGETRequest(pokemonUrl);

        try {
            pokemonJSON = new JSONObject(result);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return pokemonJSON;
    }

    public static Bitmap getPokemonSprite(String spriteUrl) {
        Bitmap sprite = NetworkAdapter.getBitmapFromUrl(spriteUrl);
        return sprite;
    }
}
