package com.vivekvishwanath.android_sprint3_challenge;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class PokemonListAdapter extends RecyclerView.Adapter<PokemonListAdapter.ViewHolder> {

    ArrayList<Pokemon> searchedPokemon;
    Context context;

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
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final Pokemon pokemon = searchedPokemon.get(position);
        holder.pokemonName.setText(pokemon.getName().toUpperCase());
        holder.pokemonListLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ViewPokemon.class);
                intent.putExtra(Constants.POKEMON_INTENT_KEY, pokemon);
                context.startActivity(intent);
            }
        });

        holder.pokemonListLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                searchedPokemon.remove(position);
                notifyItemRemoved(position);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return searchedPokemon.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout pokemonListLayout;
        TextView pokemonName;

        public ViewHolder(View itemView) {
            super(itemView);

            pokemonListLayout = itemView.findViewById(R.id.pokemon_list_layout);
            pokemonName = itemView.findViewById(R.id.pokemon_name);
        }
    }
}
