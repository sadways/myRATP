package com.esgi.myratp.repository;

import java.util.List;

import com.esgi.myratp.models.Station;

public interface IStationRepository
{
    void insertStation(Station station);
    List<Station> getAllStation();
    Station getByNom(String nom);
    Station getByType(String type);
    void updateStation(Station station);
}
