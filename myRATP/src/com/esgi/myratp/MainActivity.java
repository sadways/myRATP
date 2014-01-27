package com.esgi.myratp;

import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);		

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
    public boolean onOptionsItemSelected(MenuItem item) {
       switch (item.getItemId()) {
          case R.id.news:
        	  Intent intent = new Intent(MainActivity.this, New_station.class);
        	  startActivity(intent);
        	  Toast.makeText(MainActivity.this, "Ajouter une station", Toast.LENGTH_SHORT).show();
        	  return true;
          case R.id.update:
              Toast.makeText(MainActivity.this, "Modifier/Supprimer une station", Toast.LENGTH_SHORT).show();
              return true;
          case R.id.filter:
              Toast.makeText(MainActivity.this, "Filtrer", Toast.LENGTH_SHORT).show();
              return true;
         case R.id.go:
             finish();
             return true;
       }
       return false;}
}
