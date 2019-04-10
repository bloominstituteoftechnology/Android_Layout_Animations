package com.rybarstudios.imageviewer;

import android.net.Uri;

import java.io.Serializable;

public class ImageData implements Serializable {

    private String name;
    private String uri;
    private int id;

    public ImageData(String name, String uri) {
        this.name = name;
        this.uri = uri;
    }

    public ImageData(Uri uri, int id) {
        this.uri = uri.toString();
        this.id = id;
        this.name = "Image" + id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Uri getUri() {
        return Uri.parse(uri);
    }

    public void setUri(Uri uri) {
        this.uri = uri.toString();
    }


}
