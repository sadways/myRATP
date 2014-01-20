package com.esgi.myratp.repository;

import java.util.List;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;

import com.esgi.myratp.application.MyRATPApplication;
import com.esgi.myratp.database.MyRATPBDD;
import com.esgi.myratp.mapper.IStationMapper;
import com.esgi.myratp.mapper.StationMapperCursor;
import com.esgi.myratp.models.Station;

public class StationRepository implements IStationRepository
{
    private static final SQLiteDatabase _stationdb = MyRATPBDD.getStations().getWritableDatabase();
    private static final IStationMapper<Cursor, List<Station>> _stationMapper = new StationMapperCursor();
    
    private static StationRepository _instance;
    public static StationRepository GetInstance()
    {
        if(null == _instance)
            _instance = new StationRepository();
        return _instance;
    }

    @Override
    public void insertStation(Station station)
    {
        ContentValues values = new ContentValues();
        values.put("stationLatitude", station.getLatitude());
        values.put("stationLongitue", station.getLongitude());
        values.put("stationNom", station.getNom());
        values.put("stationVille", station.getVille());
        values.put("stationType", station.getType());

        _stationdb.insert("Stations", null, values);
        
    }

    @Override
    public List<Station> getAllStation()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Station getByNom(String nom)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Station getByType(String type)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void updateStation(Station station)
    {
        // TODO Auto-generated method stub
        
    }

}
