package com.esgi.myratp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class StationsTable extends SQLiteOpenHelper {
    
    
    public static final String TABLE = "Stations";
    public static final String ID = "idStation";
    public static final String LATITUDE = "stationLatitude";
    public static final String LONGITUDE = "stationLongitude";
    public static final String NOM = "stationNom";
    public static final String VILLE = "stationVille";
    public static final String TYPE = "stationType";


    

	public StationsTable(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
