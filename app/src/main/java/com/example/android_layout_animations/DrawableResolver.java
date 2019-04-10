package com.example.android_layout_animations;

import java.util.ArrayList;

public class DrawableResolver {
    private static final String FILM = "starwars_episode";

    public static int getDrawableId( int episodeId){
        int drawable;
        drawable = films[episodeId];
        return drawable;
    }

    private static final int[] films = new int[]{
            R.drawable.yoda,
            R.drawable.starwars_episode1,
            R.drawable.starwars_episode2,
            R.drawable.starwars_episode3,
            R.drawable.starwars_episode4,
            R.drawable.starwars_episode5,
            R.drawable.starwars_episode6,
            R.drawable.starwars_episode7
    };
}
