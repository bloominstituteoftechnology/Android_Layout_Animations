package com.lambda.android_layout_animations;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static PocketMonsters pocketMonsters;
    private static LinearLayout ll;
    private static EditText et;
    private static String strDebug;
 //   private static final String BASE_URL       = "https://pokeapi.co/api/v2/pokemon/?offset=0&limit=964";
 //   private static final String READ_ALL_URL = BASE_URL ;
    private Context context;

    @Override
    protected void onResume() {
        super.onResume();


        Pokemon pk=receiveData();

        if(pk!=null)       {
            pocketMonsters=pocketMonsters.update(pk);
            Toast.makeText(getApplicationContext(),et.getText().toString(), Toast.LENGTH_SHORT).show();

       //     et.setText("\b");
        //    et.setText(strDebug  );
     //       et.setHint( strDebug  );
            TextView tv=findViewById( R.id.text_debug );
            tv.setText( et.getText() );
          //  addFoundPokemon( pk );
        }
        PocketMonsters saved=receiveSavedData();
        if(saved!=null)pocketMonsters.update( saved );

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );


        Button bt=findViewById( R.id.button_to_search );
        bt.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et=findViewById( R.id.et_entry );
                context=getApplicationContext();
                ll=findViewById( R.id.ll_found );
                strDebug=et.getText().toString();
                Pokemon pokemonFound=pocketMonsters.findByID( et.getText().toString() ); //by number
                if(pokemonFound==null){
                    ArrayList<Pokemon> pm=pocketMonsters.findByPartialName( et.getText().toString() );
                    if(pm.size()==0){
                        ll.addView(addFoundPokemon(null));
                    }else{
                        for(int i=0;i<pm.size();i++) {
                            ll.addView( addFoundPokemon( pm.get( i ) ) );
                        }
                    }



                }else{
                    ll.addView(addFoundPokemon( pokemonFound) );//found by ID

                }

            }
        } );

        Button bts=findViewById( R.id.button_to_saved );

        bts.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendDataToSaved();

            }
        } );


    }

    private View addFoundPokemon(final Pokemon pokemonFound){
        final TextView tv =new TextView( context);
        tv.setTextSize( 30 );

        if (pokemonFound == null) {
            tv.setText( "Not Found" );
            tv.setBackgroundResource( R.drawable.ic_launcher_foreground);
            return tv;
        }else{
            LinearLayout ll=new LinearLayout( context );
            tv.setText( pokemonFound.getID()+","+pokemonFound.getName()+" " );
            final CheckBox cb=new CheckBox( context );
            cb.setChecked( pokemonFound.isSaved() );
            cb.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(pokemonFound.isSaved()==true ){
                        cb.setChecked( false );
                        pokemonFound.setSaved( false );
                        tv.setBackgroundColor( Color.BLUE );
                        tv.setTextColor(Color.WHITE  );

                    }else {
                        cb.setChecked( true );
                        pokemonFound.setSaved( true );
                        tv.setBackgroundColor( Color.WHITE );
                        tv.setTextColor(Color.BLACK  );
                    }

                }
            } );
        if(pokemonFound.isSaved()==true ){
                tv.setBackgroundColor( Color.BLUE );
                tv.setTextColor(Color.WHITE  );
            }else {
                tv.setBackgroundColor( Color.WHITE );
                tv.setTextColor(Color.BLACK  );
            }
             ll.addView( cb );
            ll.addView(tv);

            ll.setTag( pokemonFound.getID());
            if(pokemonFound.getBitmap(  )!=null){
                try {
                    ImageView iv = new ImageView( context );
                    iv.setImageBitmap( pokemonFound.getBitmap() );
                    ll.addView( iv );
                }catch (Exception e){
                    e.getMessage();
                }
            }
            ll.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sendData( pokemonFound.getID() );
                }
            } );

            return ll;
        }


    }

    private void sendData(String strID){
        Pokemon found=pocketMonsters.findByID( strID );
        if(found==null)return;
        Intent intent = new Intent(context, FullscreenActivity.class);
        intent.putExtra("DATA", found);
        startActivity(intent);
    }
    private Pokemon receiveData(){
        Pokemon pokemon=(Pokemon) getIntent().getParcelableExtra(  "DATA");
        //    pokemonCurrent=pokemon;
        return pokemon;

    }

    private void sendDataToSaved(){
        PocketMonsters saved=new PocketMonsters( pocketMonsters.findSaved());
        if(saved.size()==0)return;
        context=getApplicationContext();
        Intent intent = new Intent(context, SavedPocketMonsters.class);
        intent.putExtra("DATA_SAVED", saved);
        startActivity(intent);

    }
    private PocketMonsters receiveSavedData(){
        PocketMonsters pokemon=(PocketMonsters) getIntent().getParcelableExtra(  "DATA_SAVED");
        //    pokemonCurrent=pokemon;
        return pokemon;

    }
}


