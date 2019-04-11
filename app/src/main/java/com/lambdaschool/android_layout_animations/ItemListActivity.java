package com.lambdaschool.android_layout_animations;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lambdaschool.android_layout_animations.dummy.DummyContent;

import java.util.ArrayList;
import java.util.List;

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
        getData();

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
        //getData();
    }

    private void getData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.i(TAG, "Beginning retrieval of all objects...");
                loremPicsumArrayList = LoremPicsumDao.getAllLoremPicsumObjects();
                Bitmap bitmap = null;

                for (int i = 0; i < loremPicsumArrayList.size(); ++i) {
//                    bitmap = LoremPicsumDao.getOneLoremPicsumPhoto(loremPicsumArrayList.get(i));
//                    loremPicsumArrayList.get(i).setBitmap(bitmap);
                    final int counter = i;

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            simpleItemRecyclerViewAdapter.notifyItemChanged(counter - 1);
                            Log.i(TAG, "Notifying RecyclerView of changes at " + counter);
                        }
                    });
                }
            }
        }).start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static class SimpleItemRecyclerViewAdapter extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final ItemListActivity mParentActivity;
        private final ArrayList<LoremPicsum> mValues;
        private final boolean mTwoPane;

        SimpleItemRecyclerViewAdapter(ItemListActivity parent, ArrayList<LoremPicsum> items, boolean twoPane) {
            mValues = items;
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
            LoremPicsum loremPicsum = mValues.get(position);
            holder.mIdView.setText(String.valueOf(loremPicsum.getId()));
            holder.mContentView.setText(loremPicsum.getAuthor());

            holder.itemView.setTag(loremPicsum);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    LoremPicsum item = (LoremPicsum) view.getTag();
                    if (mTwoPane) {
                        Bundle arguments = new Bundle();
                        arguments.putString(ItemDetailFragment.ARG_ITEM_ID, String.valueOf(item.getId()));
                        ItemDetailFragment fragment = new ItemDetailFragment();
                        fragment.setArguments(arguments);
                        mParentActivity.getSupportFragmentManager().beginTransaction()
                                .replace(R.id.item_detail_container, fragment)
                                .commit();
                    } else {
                        Context context = view.getContext();
                        Intent intent = new Intent(context, ItemDetailActivity.class);
                        intent.putExtra(ItemDetailFragment.ARG_ITEM_ID, item.getId());

                        context.startActivity(intent);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            final TextView mIdView;
            final TextView mContentView;

            ViewHolder(View view) {
                super(view);
                mIdView = (TextView) view.findViewById(R.id.id_text);
                mContentView = (TextView) view.findViewById(R.id.content);
            }
        }
    }
}
