package com.lambda.android_layout_animations;

import org.json.JSONException;
import org.json.JSONObject;

// S04M03-4 start dao
public class SwApiDao {
    private static final String BASE_URL = "https://swapi.co/api/";
    private static final String PERSON_URL = BASE_URL + "people/";
    private static final String STARSHIP_URL = BASE_URL + "starships/";

    // S04M03-5 write method to get person
    public static Pokemon getPerson(int id) {
        final String result = NetworkAdapter.httpRequest(PERSON_URL + id);

        Pokemon object = null;
        try {
            JSONObject json = new JSONObject(result);

       //     object = new Pokemon(id, json.getString("name"));
        //    object.setCategory(DrawableResolver.CHARACTER);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return object;
    }

    public static Pokemon getStarship(int id) {
        final String result = NetworkAdapter.httpRequest(STARSHIP_URL + id);

        Pokemon object = null;
        try {
            JSONObject json = new JSONObject(result);

         //   object = new Pokemon(id, json.getString("name"));
        //    object.setCategory(DrawableResolver.STARSHIP);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return object;
    }
}
