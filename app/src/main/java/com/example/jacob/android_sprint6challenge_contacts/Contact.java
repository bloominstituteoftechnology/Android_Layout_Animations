package com.example.jacob.android_sprint6challenge_contacts;

import org.json.JSONException;
import org.json.JSONObject;

public class Contact {
    int id;
    String name;
    String phone;
    String email;
    String thumbImageUrl;
    String largeImageUrl;

    public Contact(int id, String name, String phone, String email, String thumbImageUrl, String largeImageUrl) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.thumbImageUrl = thumbImageUrl;
        this.largeImageUrl = largeImageUrl;
    }

    public Contact(JSONObject json, int inputId) {
        this.id = inputId;
        try {
            JSONObject names = json.getJSONObject("name");
            String fullName = "";

            fullName += names.getString("title") + " ";
            fullName += names.getString("first") + " ";
            fullName += names.getString("last") + " ";

            this.name = capitalize(fullName);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            this.phone = json.getString("phone");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            this.email = json.getString("email");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONObject imageUrls = null;
        try {
            imageUrls = json.getJSONObject("picture");
            this.thumbImageUrl = imageUrls.getString("thumbnail");
            this.largeImageUrl = imageUrls.getString("large");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String capitalize(String inputString) {
        StringBuilder builder = new StringBuilder();
        String[] wordArray = inputString.split(" ");
        for (String word : wordArray) {
            if (word.length() > 0) {
                if (word.equals("mr") || word.equals("mrs") || word.equals("ms")) {
                    word += ".";
                }
                builder.append(word.substring(0, 1).toUpperCase());
                builder.append(word.substring(1, word.length()));
                builder.append(' ');
            }
        }
        return builder.toString();
    }


}
