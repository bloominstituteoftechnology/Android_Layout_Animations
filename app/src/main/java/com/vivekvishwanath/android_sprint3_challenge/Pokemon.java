package com.vivekvishwanath.android_sprint3_challenge;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Pokemon implements Parcelable {

    private String name, spriteUrl;
    private int id;
    private ArrayList<String> abilities, moves, types;

    public Pokemon(String name, String spriteUrl, int id,
                   ArrayList<String> abilities, ArrayList<String> moves,
                   ArrayList<String> types) {
        this.name = name;
        this.spriteUrl = spriteUrl;
        this.id = id;
        this.abilities = abilities;
        this.moves = moves;
        this.types = types;
    }

    public Pokemon (JSONObject pokemonJSON) {
        if (pokemonJSON != null) {
            try {
                this.id = pokemonJSON.getInt("id");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                this.name = pokemonJSON.getString("name");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                this.spriteUrl = pokemonJSON.getJSONObject("sprites").getString("front_default");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            this.abilities = new ArrayList<>();
            try {
                JSONArray abilitiesJSONArray = pokemonJSON.getJSONArray("abilities");
                for (int i = 0; i < abilitiesJSONArray.length(); i++) {
                    this.abilities.add(abilitiesJSONArray.getJSONObject(i).getJSONObject("ability").getString("name"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            this.moves = new ArrayList<>();
            try {
                JSONArray movesJSONArray = pokemonJSON.getJSONArray("moves");
                for (int i = 0; i < movesJSONArray.length(); i++) {
                    this.moves.add(movesJSONArray.getJSONObject(i).getJSONObject("move").getString("name"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            this.types = new ArrayList<>();
            try {
                JSONArray typesJSONArray = pokemonJSON.getJSONArray("types");
                for (int i = 0; i < typesJSONArray.length(); i++) {
                    this.types.add(typesJSONArray.getJSONObject(i).getJSONObject("type").getString("name"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    protected Pokemon(Parcel in) {
        name = in.readString();
        spriteUrl = in.readString();
        id = in.readInt();
        Object[] parcelArray = in.readArray(String.class.getClassLoader());
        abilities = new ArrayList<>();
        for (int i = 0; i < parcelArray.length; i++) {
            abilities.add((String)parcelArray[i]);
        }
        parcelArray = in.readArray(String.class.getClassLoader());
        moves = new ArrayList<>();
        for (int i = 0; i < parcelArray.length; i++) {
            moves.add((String)parcelArray[i]);
        }
        parcelArray = in.readArray(String.class.getClassLoader());
        types = new ArrayList<>();
        for (int i = 0; i < parcelArray.length; i++) {
            types.add((String)parcelArray[i]);
        }
    }

    public static final Creator<Pokemon> CREATOR = new Creator<Pokemon>() {
        @Override
        public Pokemon createFromParcel(Parcel in) {
            return new Pokemon(in);
        }

        @Override
        public Pokemon[] newArray(int size) {
            return new Pokemon[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getSpriteUrl() {
        return spriteUrl;
    }

    public int getId() {
        return id;
    }

    public ArrayList<String> getAbilities() {
        return abilities;
    }

    public ArrayList<String> getMoves() {
        return moves;
    }

    public ArrayList<String> getTypes() {
        return types;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.spriteUrl);
        dest.writeInt(this.id);
        dest.writeArray(this.abilities.toArray());
        dest.writeArray(this.moves.toArray());
        dest.writeArray(this.types.toArray());
    }
}
