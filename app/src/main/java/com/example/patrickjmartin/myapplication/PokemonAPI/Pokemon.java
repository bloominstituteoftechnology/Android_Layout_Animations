package com.example.patrickjmartin.myapplication.PokemonAPI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class Pokemon implements Serializable {


    private String name, spriteURL, type1, type2;
    private int ID;
    private ArrayList<String> moves;
    private boolean isSaved;

    public Pokemon(String name, String spriteURL, String type1, String type2, int ID, ArrayList<String> moves) {
        this.name = name;
        this.spriteURL = spriteURL;
        this.type1 = type1;
        this.type2 = type2;
        this.ID = ID;
        this.moves = moves;

    }

    public Pokemon(JSONObject caught) {
        JSONObject temp;
        JSONArray tempAry;
        this.moves = new ArrayList<String>();
        String raw = null;

        try {
            raw = caught.getString("name");
            this.name = raw.substring(0, 1).toUpperCase() + raw.substring(1);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            temp = caught.getJSONObject("sprites");
            this.spriteURL = temp.getString("front_default");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            tempAry = caught.getJSONArray("types");
            raw = tempAry.getJSONObject(0)
                    .getJSONObject("type")
                    .getString("name");
            this.type1 = raw.substring(0, 1).toUpperCase() + raw.substring(1);
            raw = tempAry.getJSONObject(1)
                    .getJSONObject("type")
                    .getString("name");
            this.type2 = raw.substring(0, 1).toUpperCase() + raw.substring(1);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            this.ID = caught.getInt("id");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            tempAry = caught.getJSONArray("moves");
            for (int i = 0; i < tempAry.length(); i++) {
                moves.add(tempAry.getJSONObject(i).getJSONObject("move").getString("name"));
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        this.isSaved = false;

    }

    public String getName() {
        return name;
    }

    public String getSpriteURL() {
        return spriteURL;
    }

    public String getType1() {
        return type1;
    }

    public String getType2() {
        return type2;
    }

    public void setSaved(boolean saved) {
        isSaved = true;
    }

    public int getID() {
        return ID;
    }

    public ArrayList<String> getMoves() {
        return moves;
    }

}
