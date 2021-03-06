package com.esgi.myratp;

import java.io.IOException;

import com.esgi.myratp.dao.RatpDao;
import com.esgi.myratp.models.Station;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Update_station extends Activity implements OnClickListener{
	private EditText txtName;
	private EditText txtLigne;
	private EditText txtLocalite;
	private EditText txtLatitude;
	private EditText txtLongitude;
	private Button btnSave;
	private RatpDao dao;
	private Station station;
	private Boolean hasToUpdate;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update_station);
		try {
			dao = new RatpDao(this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		txtName = (EditText)findViewById(R.id.new_name);
		txtLigne = (EditText)findViewById(R.id.new_ligne);
		txtLocalite = (EditText)findViewById(R.id.new_localite);
		txtLatitude = (EditText)findViewById(R.id.new_latitude);
		txtLongitude = (EditText)findViewById(R.id.new_longitude);
		btnSave = (Button)findViewById(R.id.btn_new_station);
		
		this.hasToUpdate = this.getIntent().getExtras().getBoolean("update");
		if (hasToUpdate){
			int elementId = this.getIntent().getExtras().getInt("ID");
			station = dao.getElementById(elementId);
			if (null != station) {
				txtName.setText(station.getNom());
				txtLigne.setText(station.getType());
				txtLocalite.setText(station.getVille());
				txtLatitude.setText(Float.toString(station.getLatitude()));
				txtLongitude.setText(Float.toString(station.getLongitude()));
			}
		}
		
		btnSave.setOnClickListener(this);
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
        	  Intent intent = new Intent(Update_station.this, MainActivity.class);
        	  startActivity(intent);
        	  finish();
              return true;
       }
       return false;
	}

	@Override
	public void onClick(View arg0) {
		if (txtName.getText().toString().length() > 0
			&& txtLigne.getText().toString().length() > 0
			&& txtLocalite.getText().toString().length() > 0
			&& txtLatitude.getText().toString().length() > 0
			&& txtLongitude.getText().toString().length() > 0) {
			this.SaveData();
			finish();
		}
		else {
			AlertDialog.Builder build = new AlertDialog.Builder(Update_station.this);
			build.setTitle("Champs incomplets");
			build.setMessage("Veuillez remplir les champs correctement");
			build.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                       dialog.cancel();
                }
            });
			build.create().show();
		}
		
		finish();
	}
	
	private final void SaveData(){
		ContentValues values = new ContentValues();
		values.put(dao.NOM, txtName.getText().toString());
		values.put(dao.TYPE, txtLigne.getText().toString());
		values.put(dao.VILLE, txtLocalite.getText().toString());
		values.put(dao.LATITUDE, Float.parseFloat(txtLatitude.getText().toString()));
		values.put(dao.LONGITUDE, Float.parseFloat(txtLongitude.getText().toString()));
		
		if (this.hasToUpdate){
			dao.updateStation(station.getId(), values);
		} else {
			dao.addStation(values);
		}
	}
}
