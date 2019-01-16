package com.joshuahalvorson.android_fragments_basics;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.joshuahalvorson.android_fragments_basics.MtgCardFragment.OnListFragmentInteractionListener;
import java.util.List;

import io.magicthegathering.javasdk.resource.Card;


public class MtgCardRecyclerViewAdapter extends RecyclerView.Adapter<MtgCardRecyclerViewAdapter.ViewHolder> {

    private final List<Card> mValues;
    private final OnListFragmentInteractionListener mListener;
    Activity activity;

    public MtgCardRecyclerViewAdapter(Activity activity, List<Card> mtgCards, OnListFragmentInteractionListener listener) {
        this.activity = activity;
        mValues = mtgCards;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_mtgcard, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        Glide
                .with(activity.getApplicationContext())
                .load(mValues.get(position).getImageUrl())
                .thumbnail(.5f)
                .into(holder.image);

        holder.author.setText(mValues.get(position).getName());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem, holder.image);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView image;
        public final TextView author;
        public Card mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            image = view.findViewById(R.id.image);
            author = view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + author.getText() + "'";
        }
    }
}
