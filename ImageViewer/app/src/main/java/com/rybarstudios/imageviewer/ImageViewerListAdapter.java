package com.rybarstudios.imageviewer;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
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
import android.widget.TextView;

import java.util.ArrayList;

public class ImageViewerListAdapter extends RecyclerView.Adapter<ImageViewerListAdapter.ImageListViewHolder> {

    ArrayList<ImageData> imageList;
    private int lastPosition = -1;

    public ImageViewerListAdapter(ArrayList<ImageData> imageList) {
        this.imageList = imageList;
    }

    @NonNull
    @Override
    public ImageListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_details_list_item, parent, false);
        return new ImageListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ImageListViewHolder imageListViewHolder, final int position) {
        final ImageData data = imageList.get(position);

        imageListViewHolder.mImageView.setImageURI(data.getUri());
        imageListViewHolder.mTextView.setText(data.getName());
        setEnterAnimation(imageListViewHolder.parentLayout, position);

        imageListViewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent imageDetails = new Intent(v.getContext(), DetailsActivity.class);
                imageDetails.putExtra(Intent.EXTRA_STREAM, data);

                ((Activity)v.getContext()).startActivity(imageDetails, ActivityOptions.makeSceneTransitionAnimation((Activity)v.getContext()).toBundle());

            }
        });
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
        return this.imageList.size();
    }

    class ImageListViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;
        TextView mTextView;
        View parentLayout;

        public ImageListViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_view);
            mTextView = itemView.findViewById(R.id.image_name_view);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }

}
