package com.esgi.myratp;

import android.os.Bundle;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Toast;

import java.util.List;

import com.esgi.myratp.adapter.StationAdapter;
import com.esgi.myratp.dao.RatpDao;
import com.esgi.myratp.models.Station;


public class MainActivity extends ListActivity {
	private List<Station> allStations; 
    private AlertDialog.Builder build;
    private RatpDao dao;
    Intent intent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		dao = new RatpDao(this);
		this.GetAndDisplayData();
		
		//gestion du clique sur un item pour lancer la mise à jour
		this.getListView().setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				Intent intent = new Intent(getApplicationContext(), Update_station.class);
				intent.putExtra("update", true);
				intent.putExtra("ID", allStations.get(arg2).getId());
				startActivity(intent);
			}
        });
		
		//gestion du clique long pour supprimer l'enregistrement
		this.getListView().setOnItemLongClickListener(new OnItemLongClickListener(){
			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1, final int arg2, long arg3) {
				build = new AlertDialog.Builder(MainActivity.this);
                build.setTitle("Suppression de " + allStations.get(arg2).getNom());
                build.setMessage("Confirmez vous la suppression ?");
                build.setPositiveButton("Oui",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(), allStations.get(arg2).getNom() + " a été supprimé.", 3000).show();
                                dao.deleteStation(allStations.get(arg2).getId());
                                GetAndDisplayData();
                                dialog.cancel();
                            }
                        });

                build.setNegativeButton("Non", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                            }
                        });
                AlertDialog alert = build.create();
                alert.show();

                return true;
			}
		});
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
        	  Intent intent_new = new Intent(MainActivity.this, Update_station.class);
        	  intent_new.putExtra("update", false);
        	  startActivityForResult(intent_new, 0);
        	  return true;
          case R.id.filter:
        	  Intent intent_filter = new Intent(MainActivity.this, Filter_station.class);
        	  startActivityForResult(intent_filter, 1);
              return true;
          case R.id.search:
        	  Intent search_filter = new Intent(MainActivity.this, Search.class);
        	  startActivityForResult(search_filter, 2);
              return true;
         case R.id.go:
        	 finish();
             System.exit(0);
             return true;
       }
       return false;
    }
    
    @Override
    protected void onDestroy(){
    	super.onDestroy();
    	dao.kill();
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
    	super.onActivityResult(requestCode, resultCode, data);
    	switch (requestCode){
    	case 0:
    		this.GetAndDisplayData();
    		break;
    	case 1:
    	{
    		if (resultCode == RESULT_OK){
	    		Boolean metro, tramway, rer, all;
	        	metro = data.getBooleanExtra("metro", false);
	        	tramway = data.getBooleanExtra("tramway", false);
	        	rer = data.getBooleanExtra("rer", false);
	        	all = data.getBooleanExtra("all", false);
	        	
	        	allStations = dao.getFilteredStations(all, metro, rer, tramway);
	        	this.DisplayData(allStations);
    		}
    		break;
    	}
    	case 2 :
    	{
    		if (resultCode == RESULT_OK){
	    		String name = data.getStringExtra("stationName");
	        	allStations.clear();
	        	Station station = dao.getElementByName(name);
	        	if (null != station)
	        		allStations.add(station);
	        	
	        	this.DisplayData(allStations);
    		}
    		break;
    	}
    	default:
    		break;
    	}
    }

    private void GetAndDisplayData(){
    	//alimentation de la ListView
    	allStations = dao.getAllStations();
		StationAdapter adapter = new StationAdapter(this, android.R.layout.simple_expandable_list_item_1, allStations);
		this.setListAdapter(adapter);
    }
    
    private void DisplayData(List<Station> allStations){
    	//alimentation de la ListView
		StationAdapter adapter = new StationAdapter(this, android.R.layout.simple_expandable_list_item_1, allStations);
		this.setListAdapter(adapter);
    }

}
