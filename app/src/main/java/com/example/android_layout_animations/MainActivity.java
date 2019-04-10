package com.example.android_layout_animations;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
ArrayList<StarWarsFilmObject> filmList;
ListAdapter viewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getFilms();
        filmList = getFilms();
        viewAdapter = new ListAdapter(filmList);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setAdapter(viewAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }











    private ArrayList<StarWarsFilmObject> getFilms() {
        filmList = new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                StarWarsFilmObject film = null;
                for (int i = 1; i < 8; i++)
                    film = StarWarsFilmDao.getFilm(i);
                filmList.add(film);
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        viewAdapter.notifyItemChanged(filmList.size() - 1);
//                    }
//                });
            }
        }).start();
    return filmList;
    }
}
