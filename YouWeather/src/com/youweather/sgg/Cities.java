package com.youweather.sgg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class Cities{
	
    //Campi della tabella CheckInData
    public static final String ID = "_id";
    public static final String CITTA = "citta";
    public static final String PROVINCIA = "provincia";
    public static final String REGIONE = "regione";
    
    //Stringa utile per le query da mandare in esecuzione
    public static String query;
 
    //Indica il nome che la tabella avrà nel db
    public static final String TABELLA = "cities";
    public static final String[] COLONNE = new String[]{ID, CITTA, PROVINCIA, REGIONE};
 
    /**
     * Metodo usato per inserire una città nel DB
     * @param db
     * @param città
     * @param provincia
     * @param regione
     */
    public void insertCity(SQLiteDatabase db, String citta, String provincia, String regione){
        ContentValues v = new ContentValues();
        v.put(CITTA, citta);
        v.put(PROVINCIA, provincia);
        v.put(REGIONE, regione);
 
        db.insert(TABELLA, null, v);
    }
   
    /**
     * Metodo usato per caricare all'interno della tabella città, tutti gli elementi del file passato in input
     * @param db
     * @param file
     */
    public boolean load(SQLiteDatabase db,InputStream file){
    	
    	//Viene avviato il caricamento solo se le città non sono già state inserite
    	Cursor c = getAllCities(db);
    	if (c.getCount() > 0) return false;
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(file));
    	String data = "";
    	try {
			while ((data = br.readLine()) != null){
				String[] rowdata = data.split(";");
				insertCity(db, rowdata[0], rowdata[1], rowdata[2]);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if (file != null) {
			    try {
				    file.close();
				    return false;
			    } catch (IOException e) {
				
			}
		  }
		}
    	return true;
    }
    /**
     * Ritorna un cursore che punta a tutte le città contenute nel DB
     * @param db
     * @return
     */
    public Cursor getAllCities(SQLiteDatabase db){
        return db.query(TABELLA, COLONNE, null, null, null, null, null);
    }
    
	//Query che ritorna un cursore con tutte le città della regione data in input
    public Cursor getAllCitiesInRegione(SQLiteDatabase db,String region){
        query = "select * from cities where regione='"+ region+"'";
		return db.rawQuery(query, null);
    }
    
	//Query che ritorna un cursore con tutte le città della provincia data in input
    public Cursor getAllCitiesInProvincia(SQLiteDatabase db,String provincia){
        query = "select * from cities where regione='"+ provincia+"'";
		return db.rawQuery(query, null);
    }
    /**
     * Cancella la città che ha l'id passato come parametro
     * @param db
     * @param id
     * @return
     */
    public boolean deleteCity(SQLiteDatabase db, long id) {
        return db.delete(TABELLA, ID + "=" + id, null) > 0;
    }
    /**
     * Ritorna un cursore che punta alla città che ha l'id passato come parametro
     * @param db
     * @param id
     * @return
     * @throws SQLException
     */
    public Cursor getCity(SQLiteDatabase db, long id) throws SQLException {
        Cursor c = db.query(true, TABELLA, COLONNE, ID + "=" + id, null, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }
    

 
}
