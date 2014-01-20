package com.esgi.myratp.database;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.esgi.myratp.application.MyRATPApplication;

public class MyRATPBDD extends SQLiteOpenHelper {

	private static final int DB_VERSION = 1;
	
	private static String DB_PATH = "assets/";

	private static String DB_NAME = "stationdb.sqlite";

	private SQLiteDatabase myDataBase;

	private final Context myContext;
	
	private static MyRATPBDD _instance;
	
	private static StationsTable _stations;

	public MyRATPBDD(Context context) {

		super(context, DB_NAME, null, 1);
		this.myContext = context;
	}

	public static MyRATPBDD getInstance()
	{
		if(null == _instance)
		{
			_instance = new MyRATPBDD(MyRATPApplication.getContext());
		}
		return _instance;
	}
	
	public static StationsTable getStations()
	{
		if (null == _stations)
		{
			_stations = new StationsTable(MyRATPApplication.getContext(), DB_NAME, null, DB_VERSION);
		}
		
		return _stations;
	}
	
	public void createDataBase() throws IOException {

		boolean dbExist = checkDataBase();

		if (dbExist) {
		} else {

			this.getReadableDatabase();

			try {

				copyDataBase();

			} catch (IOException e) {

				throw new Error("Erreur de copie !");

			}
		}

	}

	private boolean checkDataBase() {

		SQLiteDatabase checkDB = null;

		try {
			String myPath = DB_PATH + DB_NAME;
			checkDB = SQLiteDatabase.openDatabase(myPath, null,
					SQLiteDatabase.OPEN_READONLY);

		} catch (SQLiteException e) {

		}

		if (checkDB != null) {

			checkDB.close();

		}

		return checkDB != null ? true : false;
	}

	private void copyDataBase() throws IOException {

		// Open your local db as the input stream
		InputStream myInput = myContext.getAssets().open(DB_NAME);

		// Path to the just created empty db
		String outFileName = DB_PATH + DB_NAME;

		// Open the empty db as the output stream
		OutputStream myOutput = new FileOutputStream(outFileName);

		// transfer bytes from the inputfile to the outputfile
		byte[] buffer = new byte[1024];
		int length;
		while ((length = myInput.read(buffer)) > 0) {
			myOutput.write(buffer, 0, length);
		}

		// Close the streams
		myOutput.flush();
		myOutput.close();
		myInput.close();

	}

	public void openDataBase() throws SQLException {

		// Open the database
		String myPath = DB_PATH + DB_NAME;
		myDataBase = SQLiteDatabase.openDatabase(myPath, null,
				SQLiteDatabase.OPEN_READONLY);

	}

	@Override
	public synchronized void close() {

		if (myDataBase != null)
			myDataBase.close();

		super.close();

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
