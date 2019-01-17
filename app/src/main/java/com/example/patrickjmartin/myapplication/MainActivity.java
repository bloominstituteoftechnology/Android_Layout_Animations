package com.example.patrickjmartin.myapplication;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.view.View;
import android.view.Window;



import com.example.patrickjmartin.myapplication.PokemonAPI.Pokemon;

import static com.example.patrickjmartin.myapplication.PokemonFragment.*;


public class MainActivity extends AppCompatActivity implements OnListFragmentInteractionListener {

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
             final Explode explode = new Explode();
            explode.setStartDelay(200);
            explode.setDuration(400);
            getWindow().setEnterTransition(explode);
            getWindow().setExitTransition(explode);
        }

        setContentView(R.layout.activity_main);

        context = this;
        PokemonFragment fragment = new PokemonFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_holder, fragment).commit();

        findViewById(R.id.transition_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent transitionIntent = new Intent(context, TransitionActivity.class);
                startActivity(transitionIntent);
            }
        });

    }

    @Override
    public void onListFragmentInteraction(Pokemon item) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("pokemon", item);
        Intent intent = new Intent(getApplicationContext(), PhoneDetailsActivity.class);
        intent.putExtra("pokemon", item);
        Bundle sceneAnimation = ActivityOptions.makeSceneTransitionAnimation((Activity)context).toBundle();
        startActivity(intent, sceneAnimation);
    }
}
