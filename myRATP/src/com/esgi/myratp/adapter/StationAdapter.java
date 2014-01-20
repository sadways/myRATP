package com.esgi.myratp.adapter;

import java.util.ArrayList;

import com.esgi.myratp.R;
import com.esgi.myratp.models.Station;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class StationAdapter extends BaseAdapter
{
    private ArrayList<Station> _stations;
    private Context _context;
    
    public StationAdapter(ArrayList<Station> stations, Context context)
    {
        _stations = stations;
        _context = context;
    }
    
    @Override
    public int getCount()
    {
        return _stations.size();
    }

    @Override
    public Object getItem(int arg0)
    {
        return _stations.get(arg0);
    }

    @Override
    public long getItemId(int arg0)
    {
        return arg0;
    }

    @Override
    public View getView(int arg0, View arg1, ViewGroup arg2)
    {
        View v = arg1;
        if (v == null)
        {
            LayoutInflater vi = (LayoutInflater)_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //v = vi.inflate(R.layout., null);
        }
        
        Station station = new Station();

        //ImageView image = (ImageView) v.findViewById(R.id.iconFav);
        //TextView nomVille = (TextView)v.findViewById(R.id.NomVille);
        station = _stations.get(arg0);
        
        //nomVille.setText(station.getVille());
        
        return v;
    }

}
