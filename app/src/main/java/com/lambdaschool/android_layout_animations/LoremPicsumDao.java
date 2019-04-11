package com.lambdaschool.android_layout_animations;

import android.graphics.Bitmap;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;


public class LoremPicsumDao {
    private static final String URL_LOREM_PICSUM_BASE = "https://picsum.photos/";
    private static final String URL_LOREM_PICSUM_LIST = "list";
    private static final String URL_LOREM_PICSUM_PHOTO = "%d/%d?image=%d";
    private static final int PHOTO_DIMENSION = 300;
    private static final String TAG = "LoremPicsumDao";

    public static ArrayList<LoremPicsum> getAllLoremPicsumObjects() {
        String returnedJsonAsString = NetworkAdapter.httpRequest(URL_LOREM_PICSUM_BASE + URL_LOREM_PICSUM_LIST);
        ArrayList<LoremPicsum> loremPicsumArrayList = new ArrayList<>();
        LoremPicsum loremPicsum = null;
        JSONObject jsonObject = null;
        JSONArray jsonArray = null;
        Bitmap bitmap = null;

        try {

            jsonArray = new JSONArray(returnedJsonAsString);

            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObject = jsonArray.getJSONObject(i);

                loremPicsum = new LoremPicsum(jsonObject.getInt("id"),
                        jsonObject.getInt("width"),
                        jsonObject.getInt("height"),
                        jsonObject.getString("format"),
                        jsonObject.getString("filename"),
                        jsonObject.getString("author"),
                        jsonObject.getString("author_url"),
                        jsonObject.getString("post_url"));
                Log.i(TAG, "Parsing JSON object # " + i);

                loremPicsumArrayList.add(loremPicsum);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return loremPicsumArrayList;
    }


    public static LoremPicsum getOneLoremPicsumObject(JSONObject jsonObject) {
        LoremPicsum loremPicsum = null;

        try {
            loremPicsum = new LoremPicsum(jsonObject.getInt("id"),
                    jsonObject.getInt("width"),
                    jsonObject.getInt("height"),
                    jsonObject.getString("format"),
                    jsonObject.getString("filename"),
                    jsonObject.getString("author"),
                    jsonObject.getString("author_url"),
                    jsonObject.getString("post_url"));

            Bitmap bitmap = getOneLoremPicsumPhoto(loremPicsum);
            loremPicsum.setBitmap(bitmap);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return loremPicsum;
    }

    public static String getInitialLoremPicsumData() {
        return NetworkAdapter.httpRequest(URL_LOREM_PICSUM_BASE + URL_LOREM_PICSUM_LIST);
    }

    public static Bitmap getOneLoremPicsumPhoto(LoremPicsum loremPicsum) {
        String photoAttributes = String.format(Locale.US, URL_LOREM_PICSUM_PHOTO, PHOTO_DIMENSION, PHOTO_DIMENSION, loremPicsum.getId());
        String photoUrl = String.format(Locale.US, "%s%s", URL_LOREM_PICSUM_BASE, photoAttributes);

        return NetworkAdapter.httpImageRequest(photoUrl);
    }
}