package com.esgi.myratp.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.esgi.myratp.helpers.DataBaseHelper;
import com.esgi.myratp.models.Station;

public class RatpDao {
	private DataBaseHelper dbHelper;
	private final String TABLE = "Stations";
	public final String ID = "idStation";
	public final String NOM = "nomStation";
	public final String LATITUDE = "latitudeStation";
	public final String LONGITUDE = "longitudeStation";
	public final String VILLE = "villeStation";
	public final String TYPE = "typeStation";
	
	public RatpDao(Context context){
		try {
			this.dbHelper = new DataBaseHelper(context);
			this.dbHelper.importIfNotExist();
		} catch (IOException e) {}
	}
	
	public List<Station> getAllStations(){
		List<Station> result = new ArrayList<Station>();
		Cursor sc = this.dbHelper.getDbInstance().rawQuery("SELECT * FROM " + this.TABLE, null);
        if (sc.moveToFirst()) {
            do {
            	result.add(MapStation(sc));
            } while (sc.moveToNext());
        }
        sc.close();
		return result;
	}
	
	public List<Station> getFilteredStations(Boolean all, Boolean metro, Boolean rer, Boolean tramway){
		List<Station> result = new ArrayList<Station>();
		
		String query = "SELECT * FROM " + TABLE + " where nomStation != ''";
		if (!all){
			if (metro)
				query += " and typeStation = 'metro'";
			if (rer)
				query += " and typeStation = 'rer'";
			if (tramway)
				query += " and typeStation = 'tramway'";
		}
		
		Cursor sc = this.dbHelper.getDbInstance().rawQuery(query, null);
        if (sc.moveToFirst()) {
            do {
            	result.add(MapStation(sc));
            } while (sc.moveToNext());
        }
        sc.close();
		return result;
	}
	
	public Station getElementById(int elementId){
		Cursor c = this.dbHelper.getDbInstance().rawQuery("SELECT * FROM " + this.TABLE + " WHERE idStation = " +elementId, null);
		Station station;
		if (c.moveToFirst())
			station = MapStation(c);
		else
			station = null;
		
		c.close();
		return station;
	}
	
	public Station getElementByName(String name){
		Cursor c = this.dbHelper.getDbInstance().rawQuery("SELECT * FROM " + this.TABLE + " WHERE nomStation = '" +name+"'", null);
		Station station;
		if (c.moveToFirst())
			station = MapStation(c);
		else
			station = null;
		
		c.close();
		return station;
	}
	
	public void addStation(ContentValues values){
		Cursor c = this.dbHelper.getDbInstance().rawQuery("SELECT MAX(idStation) FROM " + this.TABLE, null);
		int maxId;
		if (c.moveToFirst()){
			maxId = c.getInt(0);
		} else {
			maxId = 0;
		}
		
		if (maxId != 0){
			try {
				this.dbHelper.getDbInstance().insert(TABLE, null, values);
				Log.v("DB", "success");	
			} catch (Exception e) {
				Log.v("DB", e.getMessage());
			}
		}
	}
	
	public void updateStation(int stationId, ContentValues values){
		this.dbHelper.getDbInstance().update(this.TABLE, values, this.ID +"="+stationId, null);
	}
	
	public void deleteStation(int elementId){
		dbHelper.getDbInstance().delete(TABLE, ID+"="+elementId, null);
	}
	
	public void kill(){
		this.dbHelper.getDbInstance().close();
	}
	
	private Station MapStation(Cursor sc){
    	int id = sc.getInt(sc.getColumnIndex(ID));
    	float lat = sc.getFloat(sc.getColumnIndex(LATITUDE));
    	float lon = sc.getFloat(sc.getColumnIndex(LONGITUDE));
    	String name = sc.getString(sc.getColumnIndex(NOM));
    	String ville = sc.getString(sc.getColumnIndex(VILLE));
    	String type = sc.getString(sc.getColumnIndex(TYPE));
		
    	return new Station(id, lat, lon, name, ville, type);
	}
}
