package com.jakeesveld.android_layoutanimations;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PokemonDAO {
    public PokemonDAO() { }

    private static final String GET_URL_PREFIX = "https://pokeapi.co/api/v2/pokemon/";

    public Pokemon getPokemonById(String pID){
        try {
            String result = NetworkAdapter.httpRequest(
                    GET_URL_PREFIX + pID + "/");
            JSONObject json = new JSONObject(result);
            String pokemonId = json.getString("id");
            String pokemonName = json.getString("name");
            JSONObject sprites = json.getJSONObject("sprites");
            String spriteUrl = sprites.getString("front_default");
            JSONArray typesArray = json.getJSONArray("types");
            ArrayList<String> types = new ArrayList<>();
            for(int i=0; i < typesArray.length(); i++){
                try{
                    JSONObject type = typesArray.getJSONObject(i);
                    JSONObject typejson = type.getJSONObject("type");
                    types.add(typejson.getString("name"));
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
            JSONArray abilitiesArray = json.getJSONArray("abilities");
            ArrayList<String> abilities = new ArrayList<>();
            for(int i=0; i < abilitiesArray.length(); i++){
                JSONObject object = abilitiesArray.getJSONObject(i);
                JSONObject ability = object.getJSONObject("ability");
                abilities.add(ability.getString("name"));
            }

            Pokemon newPokemon = new Pokemon(pokemonName, spriteUrl, pokemonId, abilities, types);
            return newPokemon;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
}
}
