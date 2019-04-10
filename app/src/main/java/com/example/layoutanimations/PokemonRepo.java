package com.example.layoutanimations;

import java.util.ArrayList;

public class PokemonRepo {
    static ArrayList<Pokemon> pokemons = new ArrayList<>();


    public static ArrayList<Pokemon> getAllPokemon(){
        return pokemons;
    }

    public static void updatePokemonByName(Pokemon pokemon){
        for(int i = 0; i < pokemons.size(); i++){
            if(pokemons.get(i).getName().equals(pokemon.getName())){
                pokemons.get(i).setImage(pokemon.getImage());
                pokemons.get(i).setElementType(pokemon.getElementType());
                pokemons.get(i).setNumber(pokemon.getNumber());
            }
        }
    }

    public static void setAllPokemon(ArrayList<Pokemon> newPokemons) {
        pokemons.clear();
        pokemons.addAll(newPokemons);
    }
}
