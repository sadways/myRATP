package com.esgi.myratp.helpers;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper{
     private SQLiteDatabase myDataBase;
     private Context context;
     private static final String DATABASE_NAME = "myRATPDB.sqlite";
     public final static String DATABASE_PATH = "/data/data/com.esgi.myratp/databases/";
     public static final int DATABASE_VERSION = 1;

     public DataBaseHelper(Context context) {
         super(context, DATABASE_NAME, null, 1);
         this.context = context;
     }

     public boolean checkExist() {
         SQLiteDatabase checkDB = null;

         try {
             String myPath = DATABASE_PATH + DATABASE_NAME;
             checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
         } catch (SQLiteException e) {
             e.printStackTrace();
         } catch (Exception ep) {
             ep.printStackTrace();
         }

         if (checkDB != null) {
             checkDB.close();
         }

         return checkDB != null ? true : false;
     }

     public void importIfNotExist() throws IOException {
         boolean dbExist = checkExist();

         if (dbExist) {
                 this.myDataBase = this.getWritableDatabase();
         } else {
             try {
                 copyDatabase();
                 this.myDataBase = this.getWritableDatabase();
             } catch (IOException e) {
                 throw new Error("Error copying database");
             }
         }
     }

     private void copyDatabase() throws IOException {
    	 InputStream assestDB = context.getAssets().open(DATABASE_NAME);

         OutputStream appDB = new FileOutputStream(DATABASE_PATH + DATABASE_NAME, false);

         byte[] buffer = new byte[1024];
         int length;
         while ((length = assestDB.read(buffer)) > 0) {
             appDB.write(buffer, 0, length);
         }

         appDB.flush();
         appDB.close();
         assestDB.close();
     }
   
     public SQLiteDatabase getDbInstance(){
    	 return this.myDataBase;
     }
     
     @Override
     public void onCreate(SQLiteDatabase db) {
     }

      @Override
      public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      }
}