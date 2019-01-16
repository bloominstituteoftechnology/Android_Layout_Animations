package com.example.lambda_school_loaner_47.contactsapp;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.MessageFormat;

public class Contacts implements Parcelable {

    private String firstName, lastName;
    private String phone;
    private String email;
    private String largeImage, thumbnail;
    private Bitmap bitmap;

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Contacts(String firstName, String lastName, String phone, String email, String largeImage, String thumbnail) {
        this.firstName  = firstName;
        this.lastName   = lastName;
        this.phone      = phone;
        this.email      = email;
        this.largeImage = largeImage;
        this.thumbnail  = thumbnail;
    }

    protected Contacts(Parcel in) {
        firstName  = in.readString();
        lastName   = in.readString();
        phone      = in.readString();
        email      = in.readString();
        largeImage = in.readString();
        thumbnail  = in.readString();
        bitmap = in.readParcelable(Bitmap.class.getClassLoader());
    }

    public static final Creator<Contacts> CREATOR = new Creator<Contacts>() {
        @Override
        public Contacts createFromParcel(Parcel in) {
            return new Contacts(in);
        }

        @Override
        public Contacts[] newArray(int size) {
            return new Contacts[size];
        }
    };

    public Contacts(JSONObject jsonObject) {
        try {
            email = jsonObject.getString("email");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            phone = jsonObject.getString("phone");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            JSONObject name = jsonObject.getJSONObject("name");
            firstName       = name.getString("first");
            lastName        = name.getString("last");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            JSONObject pictureObject = jsonObject.getJSONObject("picture");
            largeImage               = pictureObject.getString("large");
            thumbnail                = pictureObject.getString("thumbnail");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(phone);
        dest.writeString(email);
        dest.writeString(largeImage);
        dest.writeString(thumbnail);
        dest.writeParcelable(bitmap, flags);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getFullName(){
        return MessageFormat.format("{0} {1}",
                this.firstName,
                this.lastName);
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String name) {
        this.firstName = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLargeImage() {
        return largeImage;
    }

    public void setLargeImage(String largeImage) {
        this.largeImage = largeImage;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
