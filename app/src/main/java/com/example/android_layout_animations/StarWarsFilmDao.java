package com.example.android_layout_animations;

import org.json.JSONException;
import org.json.JSONObject;

public class StarWarsFilmDao {
    private static final String BASE_URL = "https://swapi.co/api/";
    private static final String MOVIE_URL = BASE_URL + "films/";


    public static StarWarsFilmObject getFilm(int id){
        final String result = NetworkAdapter.httpRequest(MOVIE_URL + id);
        StarWarsFilmObject object = null;
        try {
            JSONObject jsonObject = new JSONObject(result);
            object = new StarWarsFilmObject(id, jsonObject.getString("title"));
            object.setMovieDeets(jsonObject.getString("opening_crawl"));
            object.setReleaseDate(jsonObject.getString("release_date"));
            object.setEpisodeId(jsonObject.getInt("episode_id"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    return object;
    }

}
