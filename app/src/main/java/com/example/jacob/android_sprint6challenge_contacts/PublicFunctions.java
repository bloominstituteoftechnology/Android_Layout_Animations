package com.example.jacob.android_sprint6challenge_contacts;

import android.content.Context;

import java.io.File;

class PublicFunctions {

    static String getSearchText(String inputString) {
        String outputString = inputString.substring(inputString.indexOf("api/") + 4);
        outputString = outputString.replace('/','-');
        return outputString;
    }

    static File getFileFromCache(String searchText, Context context) {
        File file = null;
        File[] items = context.getCacheDir().listFiles();
        for (File item : items) {
            if (item.getName().contains(searchText)) {
                file = item;
                break;
            }
        }
        return file;
    }

    static void deleteCache(Context context) {
        try {
            File dir = context.getCacheDir();
            if (dir != null && dir.isDirectory()) {
                deleteDir(dir);
            }
        } catch (Exception e) {}
    }

    static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (String aChildren : children) {
                boolean success = deleteDir(new File(dir, aChildren));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }



}
