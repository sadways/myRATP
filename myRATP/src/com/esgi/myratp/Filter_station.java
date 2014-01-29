package com.esgi.myratp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class Filter_station extends Activity implements OnClickListener{
	Boolean metro, rer, all, tramway;
	CheckBox cbMetro, cbAll, cbRER, cbTram;
	Button btnFilter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_filter_station);
		
		cbMetro = (CheckBox)findViewById(R.id.chkMetro);
		cbAll = (CheckBox)findViewById(R.id.chkAll);
		cbRER = (CheckBox)findViewById(R.id.chkRER);
		cbTram = (CheckBox)findViewById(R.id.chkTramway);
		btnFilter = (Button)findViewById(R.id.btn_filter_stations);
		
		cbAll.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				all = arg1;
			}
		});
		
		cbRER.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				rer = arg1;
			}
		});
		
		cbMetro.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				metro = arg1;
			}
		});
		
		cbTram.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				tramway = arg1;
			}
		});
		
		btnFilter.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.filter_station, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
	       switch (item.getItemId()) {
	          case R.id.back:
	        	  Intent intent = new Intent(Filter_station.this, MainActivity.class);
	        	  startActivity(intent);
	    		  setResult(RESULT_CANCELED, intent);
	        	  finish();
	              return true;
	       }
	       return false;
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		intent.putExtra("metro", metro);
		intent.putExtra("rer", rer);
		intent.putExtra("all", all);
		intent.putExtra("tramway", tramway);
		intent.putExtra("filter", true);
		setResult(RESULT_OK, intent);
		finish();
	}
}
