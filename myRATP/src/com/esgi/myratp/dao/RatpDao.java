package com.esgi.myratp.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;

import com.esgi.myratp.helpers.DataBaseHelper;
import com.esgi.myratp.models.Station;

public class RatpDao {
	private DataBaseHelper dbHelper;
	private final String TABLE = "Stations";
	private final String ID = "idStation";
	private final String NOM = "nomStation";
	private final String LATITUDE = "latitudeStation";
	private final String LONGITUDE = "longitudeStation";
	private final String VILLE = "villeStation";
	private final String TYPE = "typeStation";
	
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
            	int id = sc.getInt(sc.getColumnIndex(ID));
            	float lat = sc.getFloat(sc.getColumnIndex(LATITUDE));
            	float lon = sc.getFloat(sc.getColumnIndex(LONGITUDE));
            	String name = sc.getString(sc.getColumnIndex(NOM));
            	String ville = sc.getString(sc.getColumnIndex(VILLE));
            	String type = sc.getString(sc.getColumnIndex(TYPE));
            	result.add(new Station(id, lat, lon, name, ville, type));
            } while (sc.moveToNext());
        }
        sc.close();
		return result;
	}
}
