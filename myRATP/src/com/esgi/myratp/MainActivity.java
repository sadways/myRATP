package com.esgi.myratp;

import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import com.esgi.myratp.adapter.StationAdapter;
import com.esgi.myratp.dao.RatpDao;
import com.esgi.myratp.models.Station;


public class MainActivity extends ListActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//alimentation de la ListView
		RatpDao dao = new RatpDao(this);
		List<Station> allStations = dao.getAllStations();
		StationAdapter adapter = new StationAdapter(this, android.R.layout.simple_expandable_list_item_1, allStations);
		this.setListAdapter(adapter);
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
        	  Intent intent_new = new Intent(MainActivity.this, New_station.class);
        	  startActivity(intent_new);
        	  return true;
          case R.id.update:
        	  Intent intent_update = new Intent(MainActivity.this, Update_station.class);
        	  startActivity(intent_update);
              return true;
          case R.id.filter:
        	  Intent intent_filter = new Intent(MainActivity.this, Filter_station.class);
        	  startActivity(intent_filter);
              return true;
         case R.id.go:
             finish();
             return true;
       }
       return false;}
}
