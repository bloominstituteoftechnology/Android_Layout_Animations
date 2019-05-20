package com.vivekvishwanath.android_sprint3_challenge;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.concurrent.ThreadPoolExecutor;

public class PokemonListAdapter extends RecyclerView.Adapter<PokemonListAdapter.ViewHolder> {

    ArrayList<Pokemon> searchedPokemon;
    Context context;
    private int lastPosition = -1;

    public PokemonListAdapter(ArrayList<Pokemon> searchedPokemon) {
        this.searchedPokemon = searchedPokemon;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        context = parent.getContext();
        View itemView = LayoutInflater.from(context).inflate(R.layout.search_item_layout, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final Pokemon pokemon = searchedPokemon.get(position);
        holder.pokemonName.setText(pokemon.getName().toUpperCase());
        holder.pokemonImage.setImageBitmap(MainActivity.sprites.get(pokemon.getId()));
        holder.pokemonListLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ViewPokemon.class);
                intent.putExtra(Constants.POKEMON_INTENT_KEY, pokemon);
                Bundle options = ActivityOptions.makeSceneTransitionAnimation(
                        (Activity)v.getContext(),
                        holder.pokemonImage,
                        ViewCompat.getTransitionName(holder.pokemonImage)
                ).toBundle();
                context.startActivity(intent, options);
            }
        });

        holder.pokemonListLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                MainActivity.sprites.remove(pokemon.getId());
                searchedPokemon.remove(position);
                notifyItemRemoved(position);
                return true;
            }
        });
        setEnterAnimation(holder.pokemonListLayout, position);
    }

    private void setEnterAnimation(View viewToAnimate, int position) {
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(viewToAnimate.getContext(), android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }


    @Override
    public int getItemCount() {
        return searchedPokemon.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout pokemonListLayout;
        TextView pokemonName;
        ImageView pokemonImage;

        public ViewHolder(View itemView) {
            super(itemView);

            pokemonListLayout = itemView.findViewById(R.id.pokemon_list_layout);
            pokemonName = itemView.findViewById(R.id.pokemon_name);
            pokemonImage = itemView.findViewById(R.id.pokemon_image);
        }
    }

}
