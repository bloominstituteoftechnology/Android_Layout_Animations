package com.example.israel.imageviewer;

import android.graphics.BitmapFactory;
import android.net.Uri;

import java.io.File;
import java.io.Serializable;

public class ImageData implements Serializable {

    ImageData(String name, Uri uri) {
        m_name = name;
        setUriStr(uri);
    }

    private String m_name;
    private String m_uriStr;

    public String getName() {
        return m_name;
    }
    public Uri getUri() {
        return Uri.parse(m_uriStr);
    }
    public String getUriStr() {
        return m_uriStr;
    }
    private void setUriStr(Uri uri) {
        m_uriStr = uri.toString();
    }

    public String getFilePath() {
        String path = getUri().getPath();
        path = path.replace("/document/raw:", "");
        return path;
    }

    public int[] getDimension(){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(getFilePath(), options);

        int[] outInts = new int[2];
            outInts[0] = options.outWidth; // w
            outInts[1] = options.outHeight; // h

        return outInts;
    }

    public long getSize() {
        File file = new File(getFilePath());
        return file.length();
    }

}
