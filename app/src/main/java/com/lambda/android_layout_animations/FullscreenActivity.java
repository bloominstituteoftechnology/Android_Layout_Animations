package com.lambda.android_layout_animations;



import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity extends AppCompatActivity {
    private Pokemon pokemonCurrent;
    private Bitmap bitmap=null;
    Context context;
    //   LinearLayout ll;
    private TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );


        setContentView( R.layout.activity_fullscreen );
        context = getApplicationContext();
        tv = findViewById( R.id.text_deteail );

        pokemonCurrent = receiveData();


        initialView();

        if(pokemonCurrent.getBitmap()==null) {

            new Thread( new Runnable() {
                @Override
                public void run() {

                    String strID = pokemonCurrent.getID();
                    final String result = NetworkAdapter.httpRequest( "https://pokeapi.co/api/v2/pokemon/" + strID + "/" );
                    String strAbilities = "", strType = "";

                    try {
                        bitmap = NetworkAdapter.getBitmapFromUrl( "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + strID + ".png" );
                        pokemonCurrent.setBitmap( bitmap );
                        pokemonCurrent.setSpriteUrl( "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + strID + ".png" );
                        JSONObject json = new JSONObject( result );
                        strAbilities = json.getJSONArray( "abilities" ).getJSONObject( 0 ).getJSONObject( "ability" ).getString( "name" );
                        String strAbilityURL = json.getJSONArray( "abilities" ).getJSONObject( 0 ).getJSONObject( "ability" ).getString( "url" );
                        String strAbilityDetailResult = NetworkAdapter.httpRequest( strAbilityURL );
                        JSONObject json2 = new JSONObject( strAbilityDetailResult );strAbilities += "\n" + json2.getJSONArray( "flavor_text_entries" ).getJSONObject( 1 ).getString( "flavor_text" );
                        strAbilities += "\n" + json.getJSONArray( "abilities" ).getJSONObject( 1 ).getJSONObject( "ability" ).getString( "name" );
                        strAbilityURL = json.getJSONArray( "abilities" ).getJSONObject( 1 ).getJSONObject( "ability" ).getString( "url" );
                        strAbilityDetailResult = NetworkAdapter.httpRequest( strAbilityURL );
                        json2 = new JSONObject( strAbilityDetailResult );
                        strAbilities += "\n" + json2.getJSONArray( "flavor_text_entries" ).getJSONObject( 1 ).getString( "flavor_text" );

                        strType = json.getJSONArray( "types" ).getJSONObject( 0 ).getJSONObject( "type" ).getString( "name" );
                        strType += "\n" + json.getJSONArray( "types" ).getJSONObject( 1 ).getJSONObject( "type" ).getString( "name" );
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    pokemonCurrent.setAbilities( strAbilities );
                    pokemonCurrent.setTypes( strType );
                }
            } ).start();

        }
        Button bt = findViewById( R.id.dummy_button );

        bt.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendData();
                finish();
            }
        } );

        LinearLayout llp = findViewById(R.id.ll_child );
        llp.setBackgroundResource( R.drawable.ic_launcher_foreground );
        while (pokemonCurrent.getTypes().equals( "" ) && pokemonCurrent.getAbilities().equals( "" )) {

        }
        showDetail();



    }

    void showDetail(){

        Bitmap bitmap=pokemonCurrent.getBitmap();
        if(bitmap!=null){
            try {
                BitmapDrawable background = new BitmapDrawable( context.getResources(), bitmap );
                tv.setBackground( background );
            }catch(Exception e){
                e.getMessage();
            }

        }

        TextView tv = findViewById( R.id.text_deteail );

        try {
            if(!pokemonCurrent.getTypes().equals( "" )){
                tv.append( "Type=" + pokemonCurrent.getTypes());
            }else{
                tv.append("Type=unknown\n");
            }
            if(!pokemonCurrent.getAbilities().equals( "") ){
                tv.append("Abilities=" + pokemonCurrent.getAbilities() );
            }else{
                tv.append("Abilities=unknown");
            }

        }catch(Exception e){
            String error=e.getMessage();         //  tv.append( txt );

        }
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        sendData();
        finish();
    }

    void initialView(){

        tv.setText( pokemonCurrent.getID()+"\n"+pokemonCurrent.getName()+"\n"+pokemonCurrent.getAbilities());

    }


    private Pokemon receiveData(){
        Pokemon pokemon=(Pokemon) getIntent().getParcelableExtra( "DATA");
        Switch sw=findViewById( R.id.switch_saved );
        sw.setChecked( pokemon.isSaved() );
        //    pokemonCurrent=pokemon;
        return pokemon;

    }
    private void sendData(){

        Switch sw=findViewById( R.id.switch_saved );

        pokemonCurrent.setSaved(sw.isChecked());
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("DATA", pokemonCurrent);
        startActivity(intent);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView mIdView;
        final TextView mNameView, mCategoryView;
        final ImageView mImageView;
        final View      parentView;

        ViewHolder(View view) {
            super(view);
            mIdView = (TextView) view.findViewById(R.id.id_text);
            mNameView = (TextView) view.findViewById(R.id.name);
            mCategoryView = view.findViewById(R.id.category);
            mImageView = view.findViewById(R.id.image_view);
            parentView = view.findViewById(R.id.parent_view);
        }
    }
}


