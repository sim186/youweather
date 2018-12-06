package com.youweather.sgg;

import android.os.Bundle;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * StartPoint.java
 * E' l'activity iniziale che ci permette di accedere all'activity dei checkin e
 * di effettuare una ricerca di post tramite l'inserimento di una città
 */

public class StartPoint extends Activity {

	//Variabili che servono ad identificare univocamente la comunicazione tra due activity
	protected static final int CITY_REQUEST = 0;
	public static final String CITY_SELECTED = null;
	
	Button credits,check_in;
	String loc;
	public ImageButton searchb;
	EditText search;
	TextView tv;

	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_point);
        
        //Associazione degli elementi dell'interfaccia a variabili
        credits = (Button) findViewById (R.id.credits);
        tv = (TextView) findViewById (R.id.welcome);
        check_in= (Button) findViewById(R.id.check_in);
        search = (EditText) findViewById(R.id.searchcity);
        searchb = (ImageButton) findViewById(R.id.searchbutton);
        
        
        //Questo listener si occupa di lanciare l'activity che gestisce il checkin
        check_in.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {
				Intent i= new Intent(getApplicationContext(),CheckIn.class);
				startActivity(i);
				finish();
				
			}
		});
        
        //Questo listener si occupa di lanciare l'activity che contiene la lista delle città
        search.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Intent i= new Intent(StartPoint.this,City.class);
				startActivityForResult(i,CITY_REQUEST);
				
				
			}
		});
        
        
        //Questo listener si occupa di lanciare l'activity con lo stream dei post una volta inserita una città di filtro
        searchb.setOnClickListener(new View.OnClickListener() {
        	
			public void onClick(View v) {
				//Il tasto per la ricerca può essere lanciato solo se è stata inserta una città
				if(CheckIsEmpty(search.getText().toString())){	
		           Intent i= new Intent(getApplicationContext(),Stream.class);
		           i.putExtra("citta", loc);
			       startActivity(i);
				}
			}
		});
        
        credits.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), "Simone Celestino - Giuseppe Miretto", 0).show();
				
			}
		});
        
    }
    
    
    //Il metodo di occupa di raccogliere il risultato passato dall'activity City
	@Override
	public void onActivityResult (int requestCode, int resultCode, Intent data){
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode == RESULT_OK){
		    switch(requestCode){
			    case CITY_REQUEST: 
			         loc = data.getExtras().getString(CITY_SELECTED);
			        
			        search.setText(loc);
			    break;
	        }
	    }
	}
	
	//Metodo per verifica come reagisce il campo di ricerca se vuoto 
		public boolean CheckIsEmpty(String campo) {
			return !campo.isEmpty();
		}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_start_point, menu);
        return true;
    }
}
