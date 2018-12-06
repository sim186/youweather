package com.youweather.sgg;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;


public class DatabaseHelper extends SQLiteOpenHelper {

	
	//Instanza singleton del database
	private static DatabaseHelper db;
	
	//Nome del database
    public static final String NOME_DB = "youweather.db";
    //Versione del database
    public static final int VERSIONE_DB = 1;
    
    //Stringa che contiene la query per la creazione della tabella Citta
    private String CREATE_TABLE_CITTA =
        "CREATE TABLE IF NOT EXISTS " 
     + Cities.TABELLA+ " (_id integer primary key autoincrement, "
     + Cities.CITTA+ " TEXT,"
     + Cities.PROVINCIA+ " TEXT,"
     + Cities.REGIONE+ " TEXT" + ");";
    
 
	 /**
     * Costruttore dell'helper
     * @param context
     */
    private DatabaseHelper(Context context) {
        super(context, NOME_DB, null, VERSIONE_DB);
    }	
    
    //Metodo utile al pattern Singleton per la restituzione dell'unico oggetto DatabaseHelper creato
    public static DatabaseHelper getInstance(Context context){
    	if(db == null) db = new DatabaseHelper(context);
		return db;
    }
	
    /**
     * Crea le tabelle tramite il metodo exexSQL
     */
	@Override
	public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CITTA);
    }
	

	 /**
     * Nel caso l'app vada in uno stato di Upgrade verrà tentata nuovamente la creazione delle tabelle
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       // onCreate(db);
    }
    
    public boolean clearTable(SQLiteDatabase db) {
		return db.delete(Cities.TABELLA, null, null) > 0;
	}
    
    /**
     * Metodo per la chiusura del database in input
     */
    public void close(SQLiteDatabase db){
    	db.close();
    }

}
