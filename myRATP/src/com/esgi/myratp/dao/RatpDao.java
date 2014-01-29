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
	
	public RatpDao(Context context) throws IOException{
		this.dbHelper = new DataBaseHelper(context);
	}
	
	public List<Station> getAllStations(){
		List<Station> result = new ArrayList<Station>();
		try {
			Cursor sc = this.dbHelper.getDbInstance().rawQuery("select * from " + this.TABLE + " order by nomStation collate nocase asc", null);
	        if (sc.moveToFirst()) {
	            do {
	            	result.add(MapStation(sc));
	            } while (sc.moveToNext());
	        }
	        sc.close();	
		} catch (Exception e) {
			Log.v("ERROR_DB", e.getMessage());
		}
		return result;
	}
	
	public List<Station> searchStationsByName(String name){
		List<Station> result = new ArrayList<Station>();
		Cursor sc = this.dbHelper.getDbInstance().rawQuery("select * from " + this.TABLE + " where nomStation like '%" +name+"%' collate nocase", null);
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
		
		String query = "select * from " + TABLE + " where nomStation != ''";
		if (!all){
			int add = 0;
			if (metro){
				if (add > 0){
					query += " or typeStation = 'metro'";
				} else { 
					query += " and typeStation = 'metro'";
					add++;
				}
			}
			if (rer){
				if (add > 0){
					query += " or typeStation = 'rer'";
				} else { 
					query += " and typeStation = 'rer'";
					add++;
				}
			}
			if (tramway){
				if (add > 0){
					query += " or typeStation = 'tram'";
				} else { 
					query += " and typeStation = 'tram'";
					add++;
				}
			}
		}
		query += " order by nomStation collate nocase asc";
		
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
		Cursor c = this.dbHelper.getDbInstance().rawQuery("select * from " + this.TABLE + " WHERE idStation = " +elementId, null);
		Station station;
		if (c.moveToFirst())
			station = MapStation(c);
		else
			station = null;
		
		c.close();
		return station;
	}
	
	public Station getElementByName(String name){
		Cursor c = this.dbHelper.getDbInstance().rawQuery("select * from " + this.TABLE + " where nomStation = '" +name+"'", null);
		Station station;
		if (c.moveToFirst())
			station = MapStation(c);
		else
			station = null;
		
		c.close();
		return station;
	}
	
	public void addStation(ContentValues values){
		Cursor c = this.dbHelper.getDbInstance().rawQuery("select max(idStation) from " + this.TABLE, null);
		int maxId;
		if (c.moveToFirst()){
			maxId = c.getInt(0);
		} else {
			maxId = 0;
		}
		
		if (maxId != 0){
			try {
				this.dbHelper.getDbInstance().insert(TABLE, null, values);
			} catch (Exception e) {
				Log.v("DB", e.getMessage());
			}
		}
	}
	
	public void updateStation(int stationId, ContentValues values){
		try {
			this.dbHelper.getDbInstance().update(this.TABLE, values, this.ID +"="+stationId, null);	
		} catch (Exception e) {
			String ex = e.getMessage();
		}
	}
	
	public void deleteStation(int elementId){
		this.dbHelper.getDbInstance().delete(TABLE, ID+"="+elementId, null);
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
