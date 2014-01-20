package com.esgi.myratp.mapper;

import java.util.ArrayList;
import java.util.List;

import com.esgi.myratp.models.Station;

import android.database.Cursor;

public class StationMapperCursor implements IStationMapper<Cursor, List<Station>>
{

    @Override
    public List<Station> Map(Cursor item)
    {
        if(item.getCount() == 0)
            return null;
        
        List<Station> result = new ArrayList<Station>();
        
        Station station = new Station();

        int stationColumnIndex = item.getColumnIndex("idStation");
        int stationColumnLat = item.getColumnIndex("stationLatitude");
        int stationColumnLong = item.getColumnIndex("stationLongitude");
        int stationColumnNom = item.getColumnIndex("stationNom");
        int stationColumnVille = item.getColumnIndex("stationVille");
        int stationColumnType = item.getColumnIndex("stationType");
        
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
