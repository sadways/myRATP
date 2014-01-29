package com.esgi.myratp.helpers;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseHelper extends SQLiteOpenHelper{
	 
    //The Android's default system path of your application database.
    private static String DB_PATH = "/data/data/com.esgi.myratp/databases/";
 
    private static String DB_NAME = "myRATPDB.sqlite";
 
    private SQLiteDatabase myDataBase; 
 
    private final Context myContext;
 
    /**
     * Constructor
     * Takes and keeps a reference of the passed context in order to access to the application assets and resources.
     * @param context
     */
    public DataBaseHelper(Context context) {
 
    	super(context, DB_NAME, null, 1);
        this.myContext = context;
        try {
			this.createDataBase();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        this.openDataBase();
    }	
 
    public void createDataBase() throws IOException{
 
    	boolean dbExist = checkDataBase();
 
    	if(dbExist){
    		this.myDataBase = this.getWritableDatabase();
    	}else{
        	this.getReadableDatabase();
        	try {
    			copyDataBase();
    			this.myDataBase = this.getWritableDatabase();
 
    		} catch (IOException e) {
        		throw new Error("Error copying database");
 
        	}
    	}
 
    }
 
    private boolean checkDataBase(){
 
    	SQLiteDatabase checkDB = null;
 
    	try{
    		String myPath = DB_PATH + DB_NAME;
    		checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
 
    	}catch(SQLiteException e){
 
    		//database does't exist yet.
 
    	}
 
    	if(checkDB != null){
 
    		checkDB.close();
 
    	}
 
    	return checkDB != null ? true : false;
    }
 
    private void copyDataBase() throws IOException{
 
    	//Open your local db as the input stream
    	InputStream myInput = myContext.getAssets().open(DB_NAME);
 
    	// Path to the just created empty db
    	String outFileName = DB_PATH + DB_NAME;
 
    	//Open the empty db as the output stream
    	OutputStream myOutput = new FileOutputStream(outFileName);
 
    	//transfer bytes from the inputfile to the outputfile
    	byte[] buffer = new byte[1024];
    	int length;
    	while ((length = myInput.read(buffer))>0){
    		myOutput.write(buffer, 0, length);
    	}
 
    	//Close the streams
    	myOutput.flush();
    	myOutput.close();
    	myInput.close();
 
    }
 
    public void openDataBase() throws SQLException{
        String myPath = DB_PATH + DB_NAME;
    	myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    }
 
    @Override
	public synchronized void close() {
    	    if(myDataBase != null)
    		    myDataBase.close();
    	    
    	    super.close();
	}
 
	@Override
	public void onCreate(SQLiteDatabase db) {
 
	}
	
	public SQLiteDatabase getDbInstance(){
		return this.getWritableDatabase();
	}
 
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
 
	}
}