package com.example.lambda_school_loaner_47.contactsapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.MyViewHolder> {

    public static final String CONTACT_ADAPTER = "contact adapter";
    public static final String BITMAP_IMAGE    = "bitmap_image";
    Context context;
    ArrayList<Contacts> list;
    AtomicBoolean canceled = new AtomicBoolean(false);
    Bitmap bitmap = null;
    Cache cache = Cache.getInstance();

    public ContactsAdapter(ArrayList<Contacts> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ContactsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        context = parent.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.list_item_contacts, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactsAdapter.MyViewHolder myViewHolder, int i) {
        //todo replace holder
        final Contacts contact = list.get(i);
        myViewHolder.fullName.setText(contact.getFullName());
        myViewHolder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra(CONTACT_ADAPTER, contact);
                context.startActivity(intent);
            }
        });

        String key = contact.getThumbnail();
        Bitmap bm = (Bitmap) cache.getImage(key.substring(36));
        if (bm != null){
            myViewHolder.pic.setImageBitmap(bm);
        }else {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    bitmap = ContactsDao.getImage(contact.getThumbnail(),canceled);
                    canceled.lazySet(false);
                }
            }).start();
            myViewHolder.pic.setImageBitmap(bitmap);
        }

        Animation animation = AnimationUtils.loadAnimation(myViewHolder.parent.getContext(), android.R.anim.slide_in_left);
        animation.setDuration(100L);
        myViewHolder.parent.startAnimation(animation);
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull MyViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        canceled.compareAndSet(false,true);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout parent;
        TextView         fullName;
        ImageView        pic;

        public MyViewHolder(@NonNull View contact) {
            super(contact);

            parent   = contact.findViewById(R.id.parentLayout);
            fullName = contact.findViewById(R.id.tvName);
            pic      = contact.findViewById(R.id.ivThumbnail);
        }
    }
}
