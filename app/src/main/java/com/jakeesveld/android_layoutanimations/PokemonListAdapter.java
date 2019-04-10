package com.jakeesveld.android_layoutanimations;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class PokemonListAdapter extends RecyclerView.Adapter<PokemonListAdapter.PokemonViewHolder>{
    static ArrayList<Pokemon> dataList;

    public PokemonListAdapter(ArrayList<Pokemon> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_list, parent, false);
        return new PokemonViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final PokemonViewHolder pokemonViewHolder, int i) {
        final Pokemon data = dataList.get(i);

        pokemonViewHolder.idView.setText(data.getId());
        pokemonViewHolder.nameView.setText(data.getName());
        pokemonViewHolder.spriteView.setImageBitmap(data.getSpriteBitmap());
        pokemonViewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(pokemonViewHolder.parentLayout.getContext(), ViewerActivity.class);
                Pokemon passablePokemon = new Pokemon(data.getName(),
                        data.getSpriteUrl(),
                        data.getId(),
                        data.getAbilities(),
                        data.getTypes());
                intent.putExtra(Pokemon.POKEMON_INTENT_KEY, passablePokemon);
                Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(
                        ((Activity)pokemonViewHolder.parentLayout.getContext())).toBundle();

                pokemonViewHolder.parentLayout.getContext().startActivity(intent, bundle);
            }
        });

        pokemonViewHolder.parentLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                MainActivity.pokemonList.remove(data);
                Intent intent = new Intent(pokemonViewHolder.parentLayout.getContext(), ViewerActivity.class);
                pokemonViewHolder.parentLayout.getContext().startActivity(intent);
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class PokemonViewHolder extends RecyclerView.ViewHolder {
        TextView nameView;
        TextView idView;
        ConstraintLayout parentLayout;
        ImageView spriteView;

        public PokemonViewHolder(@NonNull View itemView) {
            super(itemView);
            spriteView = itemView.findViewById(R.id.image_view_sprite);
            parentLayout = itemView.findViewById(R.id.layout_parent);
            nameView = itemView.findViewById(R.id.cardview_text_name);
            idView = itemView.findViewById(R.id.cardview_text_id); }
    }
}
