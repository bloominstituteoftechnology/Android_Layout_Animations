package com.example.layoutanimations;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class PokemonDao {


    private static String BASE_URL = "https://pokeapi.co/api/v2/pokemon";
    private static String NAMES_URL = BASE_URL + "/?limit=10000";

    public static ArrayList<Pokemon> getPokemonURLS(){
        ArrayList<Pokemon> pokemons = new ArrayList<>();

        final String result = NetworkAdapter.httpRequest(NAMES_URL);
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONArray jsonResults = jsonObject.getJSONArray("results");

            for(int i = 0; i < jsonResults.length(); i++){
               JSONObject jsonPoke = jsonResults.getJSONObject(i);
               pokemons.add(new Pokemon(jsonPoke.getString("name"), jsonPoke.getString("url")));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return pokemons;
    }

    public static Pokemon getPokemon(String url) {

        String[] elementType = {"", ""};
        String imageURL = "";
        String name = "";
        int number = 0;
        ArrayList<String> movesArrList = new ArrayList<>();
        Pokemon pokemon = null;

        final String result = NetworkAdapter.httpRequest(url);
        try {
            JSONObject jsonObject = new JSONObject(result);

            try {
                name = jsonObject.getString("name");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                imageURL = jsonObject.getJSONObject("sprites").getString("front_default");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                number = Integer.parseInt(jsonObject.getString("id"));
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                JSONArray jsonArray = jsonObject.getJSONArray("types");
                for (int i = 0; i < jsonArray.length(); i++) {
                    elementType[i] = jsonArray.getJSONObject(i).getJSONObject("type").getString("name");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                JSONArray jsonArray = jsonObject.getJSONArray("moves");
                for (int i = 0; i < jsonArray.length(); i++) {
                    movesArrList.add(jsonArray.getJSONObject(i).getJSONObject("move").getString("name"));
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }

            Bitmap image = bitmapFromURL(imageURL);

            pokemon = new Pokemon(elementType, image, number, name, movesArrList);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return pokemon;
    }


    public static Bitmap bitmapFromURL(String imageURl) {
        Bitmap image = null;

        try {
            URL url = new URL(imageURl);
            image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public static void setAllPokemon(ArrayList<Pokemon> pokemons){
        PokemonRepo.setAllPokemon(pokemons);
    }

    public static ArrayList<Pokemon> getAllPokemon(){
        return PokemonRepo.getAllPokemon();
    }


    public static void updatePokemon(Pokemon pokemon) {
        PokemonRepo.updatePokemonByName(pokemon);
    }
}
