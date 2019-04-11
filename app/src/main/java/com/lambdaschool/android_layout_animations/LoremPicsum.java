package com.lambdaschool.android_layout_animations;

import android.graphics.Bitmap;

import java.io.Serializable;

public class LoremPicsum implements Serializable {

    private int id;
    private int width;
    private int height;
    private String format;
    private String filename;
    private String author;
    private String authorUrl;
    private String postUrl;
    private Bitmap bitmap;
    private final static long serialVersionUID = -7944292526602718659L;

    /**
     * No args constructor for use in serialization
     */
    public LoremPicsum() {
    }

    /**
     * @param id
     * @param width
     * @param height
     * @param format
     * @param filename
     * @param author
     * @param authorUrl
     * @param postUrl
     */
    public LoremPicsum(int id, int width, int height, String format, String filename, String author, String authorUrl, String postUrl) {
        super();
        this.id = id;
        this.width = width;
        this.height = height;
        this.format = format;
        this.filename = filename;
        this.author = author;
        this.authorUrl = authorUrl;
        this.postUrl = postUrl;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthorUrl() {
        return authorUrl;
    }

    public void setAuthorUrl(String authorUrl) {
        this.authorUrl = authorUrl;
    }

    public String getPostUrl() {
        return postUrl;
    }

    public void setPostUrl(String postUrl) {
        this.postUrl = postUrl;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

/*    @Override
    public String toString() {
        return new StringBuilder(this).append("format", format).append("width", width).append("height", height).append("filename", filename).append("id", id).append("author", author).append("authorUrl", authorUrl).append("postUrl", postUrl).toString();
    }*/

}