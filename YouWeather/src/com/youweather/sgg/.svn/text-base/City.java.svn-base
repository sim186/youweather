package com.youweather.sgg;

import java.io.IOException;
import java.util.ArrayList;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

/**
 * City.java
 * E' l'activity che mostra una listview con tutte le città selezionabili
 */

public class City extends Activity{

	//Identificativo dell'activity chiamante che aspetta una stringa contenente una città selezionata
	public static final String CITY_SELECTED = null;
	private ListView mainListView ;  //ListView che contiene l'elenco delle città
    ArrayList<String> cityList = new ArrayList<String>();  //Lista delle città 
    private ArrayAdapter<String> listAdapter ; //ListAdapter delle città
    EditText localita;
    private static Context context;
    Cities cities;
    
    //Metodo pubblico per ottenre il contesto di questa activity
    public static Context getAppContext(){
    	return City.context;
    }
    //Metodo onCreate per l'inizializzazione dell'activity
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        City.context = getApplicationContext();
        setContentView(R.layout.activity_city);
        
        mainListView = (ListView) findViewById( R.id.listcity );  //associo il layout alla listview
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(this);  //creo l'helper per aprire il DB
        SQLiteDatabase db = databaseHelper.getWritableDatabase();  //apro il DB sia in lettura che in scrittura
       
        cities = new Cities();
  		//Chiamo il metodo che caricherà il contenuto del file comuni.txt nel database
        if(loading(db)) System.err.println("Caricamento Riuscito");
        Cursor c = cities.getAllCities(db);//Assegno a un cursore tutte città trovate nel DB
               
        fillList(c);//carico l'array list con i dati restitiuti dal cursore
        listAdapter = new ArrayAdapter<String>(this, R.layout.simplerow, cityList);//aggiungiamo le entry delll'array list all'array adapter
        mainListView.setAdapter( listAdapter );//Impostiamo l'array adapter nella list view.
        
        //Listener che imposta la stringa text al valore scelto dall'utente per poi passarlo all'activity chiamante
        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View view, int arg2,
					long arg3) {
				  
				 String text = ((TextView)view).getText().toString(); 			  
    			 Intent resultIntent = new Intent();
				 resultIntent.putExtra(CITY_SELECTED, text);
				 setResult(Activity.RESULT_OK, resultIntent);
				 finish();
			}
		});
        //chiudiamo il cursore
        c.close();
    
    
    }
	
	//Caricamento del file comuni.txt
	public boolean loading(SQLiteDatabase db){
		  try {
				if(cities.load(db,this.getAssets().open("comuni.txt"))) return true;
		} 
      catch (IOException e) {
				e.printStackTrace();
		}
		return false;

	}
	
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_city, menu);
        return true;
    }
    
    protected void onDestroy()
	{
		super.onDestroy();
	}
    
    /**
     * Prende il cursore passato, lo scorre fino alla fine e aggiunge alla lista(CityList) i record trovati, 
     * In particolare mostrando nella lista il nome della città
     * @param c
     */
    
    public void fillList(Cursor c){
        while (c.moveToNext())
        {
        	cityList.add(c.getString(1));
        }
    }
}
