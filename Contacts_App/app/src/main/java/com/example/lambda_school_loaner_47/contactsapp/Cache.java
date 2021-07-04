package com.example.lambda_school_loaner_47.contactsapp;

import android.graphics.Bitmap;
import android.util.LruCache;

import java.io.Serializable;

public class Cache<T> implements Serializable {

    private static Cache instance;
    private LruCache<String, T> cacheLru;

    private Cache(){
        this.cacheLru = new LruCache<>(1024);

    }

    public static Cache getInstance(){
        if (instance == null){
            instance = new Cache();
        }

        return instance;
    }

    public void addImage(String imageName, T image){
        if (cacheLru!= null){
            cacheLru.put(imageName, image);
        }
    }

    public T getImage(String imageName){
        if (imageName != null){
            return cacheLru.get(imageName);
        }else {
        return null;}
    }

    public LruCache getLru(){
       return cacheLru;
    }
}
