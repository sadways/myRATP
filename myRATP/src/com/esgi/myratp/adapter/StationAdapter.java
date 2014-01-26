package com.esgi.myratp.adapter;

import java.util.List;

import com.esgi.myratp.R;
import com.esgi.myratp.models.Station;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class StationAdapter extends ArrayAdapter<Station>
{
	private List<Station> objets;
	
    public StationAdapter(Context context, int resource, List<Station> objects) {
		super(context, resource, objects);
		this.objets = objects;
		
    }

    @Override
    public View getView(int arg0, View arg1, ViewGroup arg2)
    {
        View v = arg1;
        if (v == null)
        {
            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.list_item_station, null);
        }
        
        Station station = objets.get(arg0);

        //ImageView image = (ImageView) v.findViewById(R.id.iconFav);
        TextView nomStation = (TextView)v.findViewById(R.id.nomStation);
        nomStation.setText(station.getNom());
        //nomVille.setText(station.getVille());
        
        return v;
    }

}
