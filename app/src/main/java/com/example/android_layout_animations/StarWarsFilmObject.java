package com.example.android_layout_animations;

import org.json.JSONObject;

public class StarWarsFilmObject {
    private String name,releaseDate,movieDeets;
    private int id, episodeId;

    public int getEpisodeId() {
        return episodeId;
    }

    public void setEpisodeId(int episodeId) {
        this.episodeId = episodeId;
    }

    public  StarWarsFilmObject(int id, String name){
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getMovieDeets() {
        return movieDeets;
    }

    public void setMovieDeets(String movieDeets) {
        this.movieDeets = movieDeets;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
