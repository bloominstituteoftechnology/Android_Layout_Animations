package com.lambdaschool.android_layout_animations;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;

/**
 * An activity representing a list of Items. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ItemDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class ItemListActivity extends AppCompatActivity {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    private SimpleItemRecyclerViewAdapter simpleItemRecyclerViewAdapter;
    private ArrayList<LoremPicsum> loremPicsumArrayList;
    private static final String TAG = "ItemListActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        loremPicsumArrayList = new ArrayList<>();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        if (findViewById(R.id.item_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        View recyclerView = findViewById(R.id.item_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        simpleItemRecyclerViewAdapter = new SimpleItemRecyclerViewAdapter(this, loremPicsumArrayList, mTwoPane);
        recyclerView.setAdapter(simpleItemRecyclerViewAdapter);
        getData();
    }

    private void getData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.i(TAG, "Beginning retrieval of all objects...");
                    //loremPicsumArrayList = LoremPicsumDao.getAllLoremPicsumObjects();
                    String returnedJsonAsString = LoremPicsumDao.getInitialLoremPicsumData();
                    LoremPicsum loremPicsum = null;
                    JSONObject jsonObject = null;
                    JSONArray jsonArray = null;

                    jsonArray = new JSONArray(returnedJsonAsString);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        jsonObject = jsonArray.getJSONObject(i);

                        loremPicsum = LoremPicsumDao.getOneLoremPicsumObject(jsonObject);
                        loremPicsumArrayList.add(loremPicsum);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                simpleItemRecyclerViewAdapter.notifyItemChanged(loremPicsumArrayList.size() - 1);
                            }
                        });
                        Log.i(TAG, "Grabbing and stuffing object " + i + " into the ArrayList and RecyclerView...");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static class SimpleItemRecyclerViewAdapter extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final ItemListActivity mParentActivity;
        private final ArrayList<LoremPicsum> loremPicsumArrayListForRecyclerView;
        private final boolean mTwoPane;
        private int lastPosition = -1;

        SimpleItemRecyclerViewAdapter(ItemListActivity parent, ArrayList<LoremPicsum> items, boolean twoPane) {
            loremPicsumArrayListForRecyclerView = items;
            mParentActivity = parent;
            mTwoPane = twoPane;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_content, parent, false);

            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            LoremPicsum loremPicsum = loremPicsumArrayListForRecyclerView.get(position);
            holder.imageViewPhoto.setImageBitmap(loremPicsum.getBitmap());
            holder.textViewId.setText(String.valueOf(loremPicsum.getId()));
            holder.textViewAuthor.setText(loremPicsum.getAuthor());
            holder.textViewDimension.setText(String.format(Locale.US, "%dx%d", loremPicsum.getWidth(), loremPicsum.getHeight()));
            holder.itemView.setTag(loremPicsum);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    LoremPicsum item = (LoremPicsum) view.getTag();
                    if (mTwoPane) {
                        Bundle arguments = new Bundle();
                        arguments.putParcelable(ItemDetailFragment.ARG_ITEM_ID, item);
                        ItemDetailFragment fragment = new ItemDetailFragment();
                        fragment.setArguments(arguments);
                        mParentActivity.getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.item_detail_container, fragment)
                                .commit();
                    } else {
                        Context context = view.getContext();
                        Intent intent = new Intent(context, ItemDetailActivity.class);
                        intent.putExtra(ItemDetailFragment.ARG_ITEM_ID, item);
                        //Bundle options = ActivityOptions.makeSceneTransitionAnimation((Activity) view.getContext()).toBundle();
                        Bundle options = ActivityOptions.makeSceneTransitionAnimation(
                                (Activity) view.getContext(),
                                holder.imageViewPhoto,
                                ViewCompat.getTransitionName(holder.imageViewPhoto)).toBundle();
                        context.startActivity(intent, options);
                    }
                }
            });

            if (position > lastPosition) {
                Animation animation = AnimationUtils.loadAnimation(holder.parentView.getContext(), android.R.anim.slide_in_left);
                holder.parentView.startAnimation(animation);
                lastPosition = position;
            }
        }

        @Override
        public int getItemCount() {
            return loremPicsumArrayListForRecyclerView.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            final ImageView imageViewPhoto;
            final TextView textViewId, textViewAuthor, textViewDimension, textViewFormat;
            final View parentView;

            ViewHolder(View view) {
                super(view);
                imageViewPhoto = view.findViewById(R.id.image_view_photo);
                textViewId = view.findViewById(R.id.text_view_id);
                textViewAuthor = view.findViewById(R.id.text_view_author);
                textViewDimension = view.findViewById(R.id.text_view_dimensions);
                textViewFormat = view.findViewById(R.id.text_view_format);
                parentView = view.findViewById(R.id.card_view_parent);
            }
        }
    }
}
