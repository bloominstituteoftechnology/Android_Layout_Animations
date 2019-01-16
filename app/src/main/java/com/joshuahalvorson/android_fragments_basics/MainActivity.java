package com.joshuahalvorson.android_fragments_basics;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Fade;
import android.view.View;
import android.view.Window;

import io.magicthegathering.javasdk.resource.Card;

public class MainActivity extends AppCompatActivity implements MtgCardFragment.OnListFragmentInteractionListener{

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MtgCardFragment fragment = new MtgCardFragment();
        context = this;
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
    }

    @Override
    public void onListFragmentInteraction(Card item, View sharedView) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("card", item);
        if(getResources().getBoolean(R.bool.is_tablet)){
            final DetailsFragment detailFragment = DetailsFragment.newInstance(item);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_detail, detailFragment).commit();
        }else{
            Intent intent = new Intent(getApplicationContext(), PhoneDetailActivity.class);
            intent.putExtra("card", item);
            Bundle sceneOptions = ActivityOptions.makeSceneTransitionAnimation(
                    (Activity)context, sharedView, sharedView.getTransitionName()).toBundle();
            //ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(
                    //(Activity)sharedView.getContext(), sharedView, sharedView.getTransitionName());
            startActivity(intent, sceneOptions);
        }
    }
}
