package com.esgi.myratp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Search extends Activity implements OnClickListener{
	private EditText txtName;
	private Button btnSearch;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		
		txtName = (EditText)findViewById(R.id.search_name);
		btnSearch = (Button)findViewById(R.id.btn_search_station);
		
		btnSearch.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search, menu);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
        case R.id.back:
      	  Intent intent = new Intent(Search.this, MainActivity.class);
      	  startActivity(intent);
		} 
      	return false;
	}
	
	@Override
	public void onClick(View arg0) {
		if (txtName.getText().toString().length() > 0){
			Intent intent = new Intent();
			intent.putExtra("stationName", txtName.getText().toString());
			setResult(RESULT_OK, intent);
			finish();
		} else {
			AlertDialog.Builder build = new AlertDialog.Builder(Search.this);
			build.setTitle("Champ incomplet");
			build.setMessage("Veuillez saisir le nom d'une station");
			build.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                       dialog.cancel();
                }
            });
			build.create().show();
		}
	}

}
