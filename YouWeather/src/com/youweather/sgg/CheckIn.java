package com.youweather.sgg;


import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CheckIn extends Activity {
	
	// Progress Dialog
	private ProgressDialog pDialog;

	// Url del corrispondente file php per l'integrazione con il database
	private static String url_create_product = "http://ip/android_connect/create_checkin.php";

	// JSON Nome del nodo
	private static final String TAG_SUCCESS = "success";

	// Instanziazione dell'oggetto JSON
	JSONParser jsonParser = new JSONParser();
	
	protected static final int CITY_REQUEST = 100;
	public static final String CITY_SELECTED = null;
	
	
    static EditText nome,localita,temperatura,condizioni;
    CheckInVerify ckv = new CheckInVerify();
    Button upload;
    
    /**
     *  Metodo onCreate che servirà ad inizializzare la nostra Activity
     *   **/
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in);
       
        //Link degli elementi dell' activity con variabili di classe
        nome = (EditText) findViewById(R.id.nome);
        localita= (EditText) findViewById(R.id.localita);
        temperatura = (EditText) findViewById(R.id.temperatura);
        condizioni = (EditText) findViewById(R.id.condizioni);
        upload = (Button) findViewById(R.id.upload);

        //Mostra l'activity contenente le città
        localita.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Intent i= new Intent(CheckIn.this,City.class);
			    startActivityForResult(i,CITY_REQUEST);
			}
		});
        
        // Questo listener serve a lanciare la comunicazione con il database tramite un thread 
        upload.setOnClickListener(new View.OnClickListener() {
        	
			public void onClick(View v) {
				String msg= ckv.CheckData();
				System.err.println(msg);
				if(!msg.equals("*")) Toast.makeText(CheckIn.this, msg, Toast.LENGTH_LONG).show();

		        // Crea un nuovo checkin tramite un thread in background 
				else    new CreateNewProduct().execute();
				
			}
		});
       
	}
        
   

	/** 
	 * Metodo che attende un risultato da un'altra activity in questo caso lo attende dalla classe City 
	 * **/
	@Override
	public void onActivityResult (int requestCode, int resultCode, Intent data){
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode == RESULT_OK){
		 
					if(requestCode == CITY_REQUEST){
					  String newText = data.getExtras().getString(CITY_SELECTED);
			        
			        localita.setText(newText);
					}
			  
	        }
	    }
	
	
	/**
	 * Task Asincrono lanciato in background per inserire un checkin
   * */
	class CreateNewProduct extends AsyncTask<String, String, String> {

		/**
		 * Azione da eseguire prima di lanciare il processo principale, ovvero,
		 * mostra una dialog di caricamento
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(CheckIn.this);
			pDialog.setMessage("Creazione CheckIn in corso. Attendere..");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		/**
		 * Metodo principale che si occupa di effetturare la richiesta http ed inserire i dati passati tramite l'oggetto JSON
		 * */
		protected String doInBackground(String... args) {
					
			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("nome", getNome()));
			params.add(new BasicNameValuePair("localita", getLocalita()));
			params.add(new BasicNameValuePair("temperatura", getTemperatura()));
			params.add(new BasicNameValuePair("condizioni", getCondizioni()));

			// Richiesta HTTP di tipo POST poichè essa coinvolge diversi parametri
			JSONObject json = jsonParser.makeHttpRequest(url_create_product,"POST", params);
			
			
			// Debug
			Log.d("Create Response", json.toString());

			// Controlla se l'operazione è andata buon fine
			try {
				int success = json.getInt(TAG_SUCCESS);
				
				if (success == 1) {
					// Se il checkin è stato inserito correttamente ritorna allo StartPoint
					Intent i = new Intent(getApplicationContext(), StartPoint.class);
					startActivity(i);
			        // closing this screen
					finish();
				} else {
					// La creazione dell'oggetto è fallita
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;
		}

		/**
		 * Metodo finale che alla fine del processo di background rimuove la dialog
		 * **/
		protected void onPostExecute(String file_url) {
			// dismiss the dialog once done
			pDialog.dismiss();
		}

	}	
	/** 
	 * Metodo che inizializza i contesti delle Activity standard options menu. *
	 * */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_check_in, menu);
        return true;
    }

 

	public static String getNome() {
		return nome.getText().toString();
	}


	public void setNome(String name) {
		CheckIn.nome.setText(name);
	}


	public static String getLocalita() {
		return localita.getText().toString();
	}


	public void setLocalita(String localita) {
		CheckIn.localita.setText(localita);	}


	public static String getTemperatura() {
		return temperatura.getText().toString();
	}


	public void setTemperatura(String temperatura) {
		CheckIn.temperatura.setText(temperatura);	}


	public static String getCondizioni() {
		return condizioni.getText().toString();
	}


	public void setCondizioni(String condizioni) {
		CheckIn.condizioni.setText(condizioni);	}
}
