package com.example.israel.imageviewer;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ImageListAdapter extends RecyclerView.Adapter<ImageListAdapter.ViewHolder>  {

    public ImageListAdapter(Context context, ArrayList<ImageData> imageDataArrayList) {
        this.context = context;
        this.imageDataArrayList = imageDataArrayList;
    }

    private Context context;
    private ArrayList<ImageData> imageDataArrayList;

    @NonNull
    @Override
    public ImageListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.image_item_layout, parent, false);

        return new ImageListAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ImageListAdapter.ViewHolder holder, int position) {

        final ImageData imageData = imageDataArrayList.get(position);
        holder.nameTextView.setText(imageData.getName());
        holder.imageImageView.setImageURI(imageData.getUri());

        holder.itemView.setAnimation(AnimationUtils.loadAnimation(holder.itemView.getContext(), android.R.anim.slide_in_left));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // open details activity
                Intent intent = new Intent(context, ImageDetailsActivity.class);
                intent.putExtra("image data", imageData);

                ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation((Activity)context, holder.imageImageView, "transition_image");
                context.startActivity(intent, activityOptions.toBundle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return imageDataArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nameTextView = itemView.findViewById(R.id.text_view_name);
            imageImageView = itemView.findViewById(R.id.image_view_image);
        }

        private TextView nameTextView;
        private ImageView imageImageView;
    }
}
