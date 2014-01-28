package com.esgi.myratp;

import com.esgi.myratp.dao.RatpDao;
import com.esgi.myratp.models.Station;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class AddOrUpdateStationActivity extends Activity {
	private EditText txtName;
	private EditText txtLigne;
	private EditText txtLocalite;
	private EditText txtLatitude;
	private EditText txtLongitude;
	private RatpDao dao;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_station);
		dao = new RatpDao(this);
		
		txtName = (EditText)findViewById(R.id.new_name);
		txtLigne = (EditText)findViewById(R.id.new_ligne);
		txtLocalite = (EditText)findViewById(R.id.new_localite);
		txtLatitude = (EditText)findViewById(R.id.new_latitude);
		txtLongitude = (EditText)findViewById(R.id.new_longitude);
		
		Boolean hasToUpdate = this.getIntent().getExtras().getBoolean("update");
		if (hasToUpdate){
			int elementId = this.getIntent().getExtras().getInt("ID");
			Station station = dao.getElementById(elementId);
			txtName.setText(station.getNom());
			txtLigne.setText(station.getType());
			txtLocalite.setText(station.getVille());
			txtLatitude.setText(Float.toString(station.getLatitude()));
			txtLongitude.setText(Float.toString(station.getLongitude()));
		}
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
        	  Intent intent = new Intent(AddOrUpdateStationActivity.this, MainActivity.class);
        	  startActivity(intent);
              return true;
       }
       return false;}

}
