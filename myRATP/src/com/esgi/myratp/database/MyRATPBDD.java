package com.esgi.myratp.database;

import java.io.IOException;

import com.esgi.myratp.application.MyRATPApplication;

public class MyRATPBDD {

	private static final int DB_VERSION = 1;

	private static final String DB_NAME = "stations.db";

	private static MyRATPBDD _instance;

	private static StationsTable _stations;

	public MyRATPBDD() {

	}

	public static MyRATPBDD getInstance() {
		if (null == _instance) {
			_instance = new MyRATPBDD();
		}
		return _instance;
	}

	public static StationsTable getStationsTable() {
		if (null == _stations) {
			_stations = new StationsTable(MyRATPApplication.getContext(),
					DB_NAME, null, DB_VERSION);
			try {
				_stations.createDataBase();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			_stations.openDataBase();
		}

		return _stations;
	}

}
