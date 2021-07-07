package com.example.layoutanimations;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.ArrayList;

public class Pokemon implements Serializable {

    private String[] elementType;
    private Bitmap image;
    private String url;
    private int number;
    private String name;
    private String[] moves;
    private boolean isComplete;
    ArrayList<String> movesArrList;

    public String getUrl() {
        return url;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public Pokemon(String[] elementType, Bitmap image, int number, String name, ArrayList<String> movesArrList) {
        this.elementType = elementType;
        this.image = image;
        this.number = number;
        this.name = name;
        isComplete = true;
        this.movesArrList = movesArrList;

        moves = new String[movesArrList.size()];
        for (int i = 0; i < moves.length; i++) {
            moves[i] = movesArrList.get(i);
        }
    }

    public void setElementType(String[] elementType) {
        this.elementType = elementType;
        isComplete = false;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Pokemon(String name, String url){
        this.name = name;
        this.url = url;
    }


    public String[] getElementType() {
        return elementType;
    }


    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public Bitmap getImage() {
        return image;
    }
}
