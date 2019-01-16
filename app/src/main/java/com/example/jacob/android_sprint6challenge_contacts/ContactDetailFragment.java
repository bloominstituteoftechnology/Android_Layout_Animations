package com.example.jacob.android_sprint6challenge_contacts;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * A fragment representing a single Contact detail screen.
 * This fragment is either contained in a {@link ContactListActivity}
 * in two-pane mode (on tablets) or a {@link ContactDetailActivity}
 * on handsets.
 */
public class ContactDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private Contact mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ContactDetailFragment() {
    }

    Context context;
    AtomicBoolean cancelStatus;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this.getActivity();
        cancelStatus = new AtomicBoolean(false);
        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = ContactListActivity.contactList.get(getArguments().getInt(ARG_ITEM_ID));
            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.name);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.contact_detail, container, false);
        cancelStatus = new AtomicBoolean(false);
        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.contact_phone)).setText(mItem.phone);
            ((TextView) rootView.findViewById(R.id.contact_email)).setText(mItem.email);

            File file = PublicFunctions.getFileFromCache(PublicFunctions.getSearchText(mItem.largeImageUrl), context);
            if (file == null) {
                ContactsDao.ObjectCallback<Bitmap> callback = new ContactsDao.ObjectCallback<Bitmap>() {
                    @Override
                    public void returnObjects(final Bitmap bitmap) {
                        if (bitmap != null) {
                                if (!cancelStatus.get()) {
                                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                                        @Override
                                        public void run() {
                                            ((ImageView) rootView.findViewById(R.id.image_large)).setImageBitmap(bitmap);
                                        }
                                    });
                                }
                        }
                    }
                };
                ContactsDao.getImageFile(mItem.largeImageUrl, context, cancelStatus, callback);
            } else {
                Bitmap bitmap;
                try {
                    bitmap = BitmapFactory.decodeStream(new FileInputStream(file));
                    ((ImageView) rootView.findViewById(R.id.image_large)).setImageBitmap(bitmap);

                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
        }

        return rootView;
    }

    @Override
    public void onPause() {
        super.onPause();
        cancelStatus.set(true);
    }
}
