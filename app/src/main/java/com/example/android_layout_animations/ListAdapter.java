package com.example.android_layout_animations;

//TODO: MAKE SURE YOU HAVE DEPENDENCIES AND THE LAYOUT YOU WANT BEFORE GOING FARTHER
//TODO: STEPS 11+ ARE ON MAIN ACTIVITY (OR WHICHEVER ACTIVITY YOU WANT TO HAVE THE LIST)
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

//TODO: 2.)has to extend RecyclerView.Adapter <ListAdapter(className).SampleViewHolder(our created nexted class)
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.SampleViewHolder> {

//TODO: 4.) create constructor (usually an arrayList to cycle through)

    ArrayList<StarWarsFilmObject> entryData;

//TODO: 5.)Ctrl N and generate the constructor for the arrayList we made


    public ListAdapter(ArrayList<StarWarsFilmObject> entryData) {
        this.entryData = entryData;
    }
//TODO: 3.) implement the methods the IDE recommends (all 3)

    @NonNull
    @Override
    //create an instance of our viewholder which is our connection to the layout
    public SampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
//TODO: 9:) Inflate Layout and use the view to create viewholder can change ^ to parent
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout,parent,false);
        return new SampleViewHolder(itemView);
    }

    @Override
    //bind an element from our list of data to the provided viewholder
    public void onBindViewHolder(@NonNull SampleViewHolder sampleViewHolder, int i) {
//TODO: 10:) bind the data from the object to the views.
        StarWarsFilmObject data = entryData.get(i);
        sampleViewHolder.tv.setText(data.getName());

        sampleViewHolder.iv.setImageDrawable();
        //reiterate all this through the object to place your items (image,names,id, etc.)
        sampleViewHolder.parentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sampleIntent = new Intent(v.getContext(),MainActivity.class);
//..............
                v.getContext().startActivity(sampleIntent);// can also use startActivityForResult(intent,requestCode)
//^ to start an intent from an onclick from outside of an activity.

            }
        });
    }

    @Override
    //used by the recyclerview to know when to stop
    public int getItemCount() {
//TODO: 8:) DON'T FORGET TO SET THE GET ITEM COUNT
        return this.entryData.size();
    }
    //TODO:  1.)Nested Class has to be made. will need to implement the method below
    //our connection to the views in the layout
    class SampleViewHolder extends RecyclerView.ViewHolder{
//TODO: 6.) Create the handles to all your views (this is the REAL class), the class its attached to is just method implementation.

        TextView tv;
        ImageView iv;
        View parentView;
        //bind the datamembers of our viewholder to the items in the layout
        public SampleViewHolder(@NonNull View itemView) {
//TODO: 7.) Attach the handles (since not in activity, will have to use itemView.findViewById() )
            super(itemView);
            tv = itemView.findViewById(R.id.list_view_title);
            iv = itemView.findViewById(R.id.list_view_image);
            parentView = itemView.findViewById(R.id.list_parent);
        }
    }
}
