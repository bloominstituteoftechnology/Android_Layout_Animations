package com.lambda.android_layout_animations;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

public class PocketMonsters implements Parcelable {
    private ArrayList<Pokemon> alPokemon;
    private static final String BASE_URL = "https://pokeapi.co/api/v2/pokemon/?offset=0&limit=964";
    private static final String READ_ALL_URL = BASE_URL;



    public PocketMonsters(Pokemon pokemon) {
        alPokemon = new ArrayList<>( 1 );
        alPokemon.add( pokemon );
    }
    //20190417 Initialize by reading from API
    public PocketMonsters(Context context) {
        alPokemon = new ArrayList<>( 1 );
        if(NetworkAdapter.isInternetConnected( context )){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //  pocketMonsters.obtainEveryoneFromAPI();


                    String strDebug="";
                    final String result = NetworkAdapter.httpRequest( READ_ALL_URL );
                    String[] strNames=result.split( "," );
                    String name="", id="";
                    for(int i=3;i<strNames.length-1;i+=2){
                        if(i==3){
                            name=strNames[i].split("\""  )[5];
                        }else{
                            name=strNames[i].split("\""  )[3];
                        }
                        id=((strNames[i+1].split("\""  )[3]).replace( "https://pokeapi.co/api/v2/pokemon/","" )).replace( "/","" );

                        Pokemon pk=new Pokemon(name,id);

                        if(alPokemon==null)                            alPokemon=new ArrayList<Pokemon>(1);

                        alPokemon.add(pk);
                    }
                }
            }).start();
        }



    }

    public PocketMonsters(ArrayList<Pokemon> alPokemon) {
        this.alPokemon = alPokemon;
    }

    protected PocketMonsters(Parcel in) {
        alPokemon = in.createTypedArrayList( Pokemon.CREATOR );
    }

    public static final Creator<PocketMonsters> CREATOR = new Creator<PocketMonsters>() {
        @Override
        public PocketMonsters createFromParcel(Parcel in) {
            return new PocketMonsters( in );
        }

        @Override
        public PocketMonsters[] newArray(int size) {
            return new PocketMonsters[size];
        }
    };

    public void add(Pokemon pokemon) {
        alPokemon.add( pokemon );
    }

    public Pokemon findByID(String strID) {
        for(int i=0;i<size();i++){
            if(this.alPokemon.get( i ).getID().equals( strID ))return this.alPokemon.get(i);
        }
        return null;

    }

    public ArrayList<Pokemon> getAlPokemon() {
        return alPokemon;
    }

    public void setAlPokemon(ArrayList<Pokemon> alPokemon) {
        this.alPokemon = alPokemon;
    }

    public Pokemon findByIDandNameString(String strIDandName) {
        String[] strTemp = strIDandName.split( "," );
        Pokemon found;
        found = findByID( strTemp[0] );
        if (found.getName().equals( strTemp[1].replace( "\n", "" ) )) {
            return found;
        } else {
            return null;
        }


    }

    public int size() {
        return alPokemon.size();
    }

    public PocketMonsters update(Pokemon pk) {
        int i=0;
        for(;i<size();i++){
            if(this.alPokemon.get( i ).getID().equals( pk.getID() )&&
                    this.alPokemon.get( i ).getName().equals( pk.getName()))break;

        }

        this.alPokemon.set(i,pk );
        return this;
    }
    public PocketMonsters update(PocketMonsters pm){
        Pokemon pk=null;
        for(int i=0;i<pm.size();i++){
            this.update( pm.getAlPokemon().get( i ) );
        }

        return this;
    }

    public ArrayList<Pokemon> findByPartialName(String strPartialName){
        ArrayList<Pokemon> found=new ArrayList<Pokemon>(  );
        for(int i=0;i<size();i++){
            if(alPokemon.get( i ).getName().contains( strPartialName)){
                found.add( alPokemon.get(i) );
            }
        }



        return found;
    }
    public ArrayList<Pokemon> findSaved(){
        ArrayList<Pokemon>alpk=new ArrayList<>( 1 );
        for(int i=0;i<size();i++){
            if(alPokemon.get( i ).isSaved()==true){
                alpk.add( alPokemon.get(i) );
            }
        }
        return alpk;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList( alPokemon );
    }
}