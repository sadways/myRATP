package com.esgi.myratp.models;

public class Station {

	private int _id;
	private float _latitude;
	private float _longitude;
	private String _nom;
	private String _ville;
	private String _type;

	public Station(int id, float latitude, float longitude, String nom, String ville, String type){
		this._id = id;
		this._latitude = latitude;
		this._longitude = longitude;
		this._nom = nom;
		this._type = type;
		this._ville = ville;
	}
	
	public int getId() {
		return _id;
	}

	public float getLatitude() {
		return _latitude;
	}

	public float getLongitude() {
		return _longitude;
	}

	public String getNom() {
		return _nom;
	}

	public String getVille() {
		return _ville;
	}

	public String getType() {
		return _type;
	}

	public void setId(int id) {
		_id = id;
	}

	public void setLatitude(float latitude) {
		_latitude = latitude;
	}

	public void setLongitude(float longitude) {
		_longitude = longitude;
	}

	public void setNom(String nom) {
		_nom = nom;
	}

	public void setVille(String ville) {
		_ville = ville;
	}

	public void setType(String type) {
		_type = type;
	}
}
