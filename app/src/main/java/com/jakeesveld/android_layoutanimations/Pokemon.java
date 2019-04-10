package com.jakeesveld.android_layoutanimations;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.ArrayList;

public class Pokemon implements Serializable {

    public static final String POKEMON_INTENT_KEY = "pokemon";
    private String name, spriteUrl, id;
    private ArrayList<String> abilities, types;
    private Bitmap spriteBitmap;

    public Pokemon(String name, String spriteUrl, String id, ArrayList<String> abilities, ArrayList<String> types) {
        this.name = name;
        this.spriteUrl = spriteUrl;
        this.id = id;
        this.abilities = abilities;
        this.types = types;
    }
    public Pokemon(String name, String spriteUrl, String id, ArrayList<String> abilities, ArrayList<String> types, Bitmap spriteBitmap) {
        this.name = name;
        this.spriteUrl = spriteUrl;
        this.id = id;
        this.abilities = abilities;
        this.types = types;
        this.spriteBitmap = spriteBitmap;
    }

/*    protected Pokemon(Parcel in) {
        name = in.readString();
        spriteUrl = in.readString();
        id = in.readString();
        abilities = in.createStringArrayList();
        types = in.createStringArrayList();
        spriteBitmap = in.readParcelable(Bitmap.class.getClassLoader());
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
    };*/

    public String getName() {
        return name;
    }

    public String getSpriteUrl() {
        return spriteUrl;
    }

    public String getId() {
        return id;
    }

    public ArrayList<String> getAbilities() {
        return abilities;
    }

    public ArrayList<String> getTypes() {
        return types;
    }

    public Bitmap getSpriteBitmap() {
        return spriteBitmap;
    }

/*    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(spriteUrl);
        dest.writeString(id);
        dest.writeArray(abilities.toArray());
        dest.writeArray(types.toArray());
        dest.writeValue(spriteBitmap);
    }*/
}