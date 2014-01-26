package com.esgi.myratp.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.esgi.myratp.database.StationsTable;
import com.esgi.myratp.models.Station;

import android.database.Cursor;

public class StationMapperCursor implements IStationMapper<Cursor, List<Station>>
{

    @Override
    public List<Station> Map(Cursor item)
    {
        if(item.getCount() == 0)
            return Collections.emptyList();
        
        List<Station> result = new ArrayList<Station>();
        
        Station station = new Station();

        int stationColumnIndex = item.getColumnIndex(StationsTable.ID);
        int stationColumnLat = item.getColumnIndex(StationsTable.LATITUDE);
        int stationColumnLong = item.getColumnIndex(StationsTable.LONGITUDE);
        int stationColumnNom = item.getColumnIndex(StationsTable.NOM);
        int stationColumnVille = item.getColumnIndex(StationsTable.VILLE);
        int stationColumnType = item.getColumnIndex(StationsTable.TYPE);
        
        while(item.moveToNext())
        {
            station.setId(item.getInt(stationColumnIndex));
            station.setLatitude(item.getFloat(stationColumnLat));
            station.setLongitude(item.getFloat(stationColumnLong));
            station.setNom(item.getString(stationColumnNom));
            station.setVille(item.getString(stationColumnVille));
            station.setType(item.getString(stationColumnType));
            result.add(station);
        }
        return result;
    }

}
