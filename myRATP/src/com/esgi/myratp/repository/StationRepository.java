package com.esgi.myratp.repository;

import java.util.List;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;

import com.esgi.myratp.application.MyRATPApplication;
import com.esgi.myratp.database.MyRATPBDD;
import com.esgi.myratp.database.StationsTable;
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
        values.put(StationsTable.LATITUDE, station.getLatitude());
        values.put(StationsTable.LONGITUDE, station.getLongitude());
        values.put(StationsTable.NOM, station.getNom());
        values.put(StationsTable.VILLE, station.getVille());
        values.put(StationsTable.TYPE, station.getType());

        _stationdb.insert(StationsTable.TABLE, null, values);
        
    }

    @Override
    public List<Station> getAllStation()
    {
        String[] columns = new String[] { StationsTable.NOM };
        Cursor c = _stationdb.query(StationsTable.TABLE, columns, null, null, null, null, null);
        List<Station> result = _stationMapper.Map(c);
        return result;
    }

    @Override
    public Station getByNom(String nom)
    {
        String[] columns = new String[] { StationsTable.NOM };
        String whereClause = StationsTable.NOM + " = \"" + nom +"\"";
        
        Cursor c = _stationdb.query(StationsTable.TABLE, columns, whereClause, null, null, null, null);
        
        List<Station> result = _stationMapper.Map(c);
        if(null == result)
            return null;
        
        if(result.size() > 0)
            return result.get(0);
        
        return null;
    }

    @Override
    public Station getByType(String type)
    {
        String[] columns = new String[] { StationsTable.NOM };
        String whereClause = StationsTable.TYPE + " = \"" + type +"\"";
        
        Cursor c = _stationdb.query(StationsTable.TABLE, columns, whereClause, null, null, null, null);
        
        List<Station> result = _stationMapper.Map(c);
        if(null == result)
            return null;
        
        if(result.size() > 0)
            return result.get(0);
        
        return null;
    }

    @Override
    public void updateStation(Station station)
    {
        // TODO Auto-generated method stub
        
    }

}
