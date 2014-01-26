package com.esgi.myratp.database;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.esgi.myratp.application.MyRATPApplication;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class StationsTable extends SQLiteOpenHelper {
    
	private static String DB_PATH = "/data/data/com.esgi.myratp/databases";

	private static String DB_NAME = "stationdb.sqlite";
	
	private String DB_FINAL;
	
	
    public static final String TABLE = "Stations";
    public static final String ID = "idStation";
    public static final String LATITUDE = "stationLatitude";
    public static final String LONGITUDE = "stationLongitude";
    public static final String NOM = "stationNom";
    public static final String VILLE = "stationVille";
    public static final String TYPE = "stationType";

	private SQLiteDatabase myDataBase;


    

	public StationsTable(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		this.DB_FINAL = name;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE " + TABLE + ";");
        onCreate(db);		
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
	
	public SQLiteDatabase getDatabase() {
        return this.myDataBase;
    }

	private boolean checkDataBase() {

		SQLiteDatabase checkDB = null;

		try {
			String myPath = DB_PATH + DB_FINAL;
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
		InputStream myInput = MyRATPApplication.getContext().getAssets().open(DB_NAME);

		// Path to the just created empty db
		String outFileName = DB_PATH + DB_FINAL;

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
		String myPath = DB_PATH + DB_FINAL;
		myDataBase = SQLiteDatabase.openDatabase(myPath, null,
				SQLiteDatabase.OPEN_READONLY);

	}

	@Override
	public synchronized void close() {

		if (myDataBase != null)
			myDataBase.close();

		super.close();

	}

}
