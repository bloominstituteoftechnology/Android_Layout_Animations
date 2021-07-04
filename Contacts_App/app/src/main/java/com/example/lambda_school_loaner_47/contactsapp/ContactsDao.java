package com.example.lambda_school_loaner_47.contactsapp;

import android.graphics.Bitmap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

class ContactsDao {

    public interface ObjectCallback<T> {
        void returnContacts(T object);
    }

    public static final String BASE_URL = "https://randomuser.me/api/?format=json&inc=name,email,phone,picture&results=1000";

    public static void getContacts(final ObjectCallback<ArrayList<Contacts>> objectCallback){

        final ArrayList<Contacts> list    = new ArrayList<>();
        String                    url     = BASE_URL;

        NetworkAdapter.NetworkCallback callback = new NetworkAdapter.NetworkCallback() {
            @Override
            public void returnResult(Boolean success, String result) {
                try {
                    JSONObject jsonObject   = new JSONObject(result);
                    JSONArray  resultsArray = jsonObject.getJSONArray("results");

                    int i = 0;

                    Contacts current;

                    while (i < resultsArray.length()){

                        JSONObject contactObject = resultsArray.getJSONObject(i);
                        current = new Contacts(resultsArray.getJSONObject(i));

                        list.add(current);
                        i++;
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }finally {
                    objectCallback.returnContacts(list);
                }
                //return list; ;
            }
        };
        NetworkAdapter.httpRequest(url, callback);

    }

    public static Bitmap getImage(String url, final AtomicBoolean atomicBoolean){
        return NetworkAdapter.httpImageRequest(url, atomicBoolean);
    }

}
