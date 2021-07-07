package com.example.patrickjmartin.myapplication.PokemonAPI;
import org.json.JSONException;
import org.json.JSONObject;

public class PokemonDAO {

    private static String BASE_URL = "https://pokeapi.co/api/v2/pokemon/";

    private static final String SELECTED_POKEMON_URL = BASE_URL + "%d" + "/";

    public static Pokemon getPokemon(String pokeID) {
        String pokeURL = pokeID;
        Pokemon iChooseYou = null;
        final String result = NetworkAdapter.httpRequest(pokeURL, NetworkAdapter.GET);
        JSONObject pokeLevel = null;

        try {
            pokeLevel = new JSONObject(result);
            iChooseYou = new Pokemon(pokeLevel);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return iChooseYou;

    }

}
