package com.esgi.myratp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

public class New_station extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_station);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_station, menu);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
       switch (item.getItemId()) {
          case R.id.back:
        	  Intent intent = new Intent(New_station.this, MainActivity.class);
        	  startActivity(intent);
              return true;
       }
       return false;}

}
