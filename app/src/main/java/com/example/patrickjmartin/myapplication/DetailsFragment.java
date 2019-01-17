package com.example.patrickjmartin.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.patrickjmartin.myapplication.PokemonAPI.Pokemon;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;



public class DetailsFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";



    private Pokemon pokemon;
    private ImageView pokemonImage;
    private TextView nameTV, type1TV, type2TV;


    public DetailsFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static DetailsFragment newInstance(Pokemon object) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, object);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            pokemon = (Pokemon)getArguments().getSerializable(ARG_PARAM1);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false);
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        pokemonImage = view.findViewById(R.id.pokemon_details_image);
        nameTV = view.findViewById(R.id.details_frag_textview);
        type1TV = view.findViewById(R.id.type1);
        type2TV = view.findViewById(R.id.type2);

        new DownloadImageTask(pokemonImage).execute(pokemon.getSpriteURL());

        nameTV.setText(pokemon.getName());
        type1TV.setText(pokemon.getType1());
        type2TV.setText(pokemon.getType2());
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */


    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        private final WeakReference<ImageView> imageViewWeakReference;
        ProgressBar progressBar;


        public DownloadImageTask(ImageView bookImageView) {
            this.imageViewWeakReference = new WeakReference<>(bookImageView);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            String url = strings[0];
            Bitmap image;
            InputStream in;
            try {
                URL imageURL = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) imageURL.openConnection();
                connection.setDoInput(true);
                connection.connect();


                in = connection.getInputStream();
                image = BitmapFactory.decodeStream(in);
                in.close();
                return image;
            } catch (IOException e) {
                e.printStackTrace();
                return  null;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            ImageView imageView = imageViewWeakReference.get();
            imageView.setImageBitmap(bitmap);


        }
    }
}

