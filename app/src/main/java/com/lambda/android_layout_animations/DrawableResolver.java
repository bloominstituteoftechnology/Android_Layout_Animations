package com.lambda.android_layout_animations;

// S04M03-2 Add Resources and Resolver
public class DrawableResolver {

    public static final String CHARACTER = "character";
    public static final String PLANET = "planet";
    public static final String STARSHIP = "starship";
    public static final String VEHICLE = "vehicle";

    public static int getDrawableId(String category, int id) {
        int drawable=0;
        try {
            switch (category) {
                case CHARACTER:
                    drawable = characters[id];
                    break;
                case PLANET:
                    drawable = planets[id];
                    break;
                case STARSHIP:
                    drawable = starships[id];
                    break;
                case VEHICLE:
                    drawable = vehicles[id];
                    break;
                default:
                //    drawable = R.drawable.placeholder;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        //    drawable = R.drawable.placeholder;
        }
        return drawable;
    }

    public static final int[] characters = new int[] {

    };
    public static final int[] films = new int[] {

    };
    public static final int[] planets = new int[] {

    };
    public static final int[] species = new int[] {

    };
    public static final int[] starships = new int[] {

    };
    public static final int[] vehicles = new int[] {

    };
}

