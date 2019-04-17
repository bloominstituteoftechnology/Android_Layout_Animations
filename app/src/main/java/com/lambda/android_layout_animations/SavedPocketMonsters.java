package com.lambda.android_layout_animations;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SavedPocketMonsters extends AppCompatActivity {
    PocketMonsters pocketMonsters;
    Context context;
    LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_saved_pocket_monsters );
        context=getApplicationContext();
        ll=findViewById( R.id.ll_saved );
        pocketMonsters=receiveData();
        showSaved(pocketMonsters);
        Button bt=findViewById( R.id.button_to_search_from_saved );
        bt.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData();
            }
        } );


    }
    void showSaved(PocketMonsters pm){

        for(int i=0;i<pm.size();i++){

            ll.addView( addSavedPokemon(  pm.getAlPokemon().get( i )));
        }


    }

    private View addSavedPokemon(final Pokemon pokemonSaved){
        final TextView tv =new TextView( context);
        tv.setTextSize( 30 );

        if (pokemonSaved == null) {
            tv.setText( "Something is wrong" );
            tv.setBackgroundResource( R.drawable.ic_launcher_foreground);
            return tv;
        }else{
            LinearLayout ll=new LinearLayout( context );
            tv.setText( pokemonSaved.getID()+","+pokemonSaved.getName()+" " );
            tv.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sendDataForDetail(pokemonSaved);
                }
            } );
            ll.addView(tv);

            if(pokemonSaved.getBitmap(  )!=null){
                try {
                    ImageView iv = new ImageView( context );
                    iv.setImageBitmap( pokemonSaved.getBitmap() );
                    ll.addView( iv );
                }catch (Exception e){
                    e.getMessage();
                }
            }
            CheckBox cb=new CheckBox( context );
            cb.setChecked( pokemonSaved.isSaved());
            cb.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(pokemonSaved.isSaved()==true){
                        pokemonSaved.setSaved( false );
                        ((CheckBox)v).setChecked( false );
                    }else{
                        pokemonSaved.setSaved( true );
                        ((CheckBox)v).setChecked( true );
                    }

                }
            } );

            ll.addView( cb );
            return ll;
        }


    }

    private void sendData(){

        if(pocketMonsters.size()==0)return;

        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("DATA_SAVED", pocketMonsters);
        startActivity(intent);
    }
    private PocketMonsters receiveData(){
        PocketMonsters pocketMonsters=(PocketMonsters) getIntent().getParcelableExtra(  "DATA_SAVED");
   //     Pokemon pokemon=(Pokemon) getIntent().getParcelableExtra(  "DATA");
   //     pocketMonsters.update( pokemon );
        return pocketMonsters;

    }
    private void sendDataForDetail(Pokemon pk){
        Pokemon found=pocketMonsters.findByID( pk.getID() );
        if(found==null)return;
        Intent intent = new Intent(context, FullscreenActivity.class);
        intent.putExtra("DATA", found);
        startActivity(intent);
    }



}
