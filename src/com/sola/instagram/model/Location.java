package com.sola.instagram.model;
import org.json.JSONObject;

public class Location extends InstagramModel {
	int id;
	String name;
	Double longitude;
	Double latitude;
	
	public Location(JSONObject obj, String accessToken) {
		super(obj, accessToken);
		setName(obj.optString("name"));
		setId(obj.optInt("id"));
		setLatitude(obj.optDouble("latitude"));
		setLongitude(obj.optDouble("longitude"));
	}
	
	public int getId() {
		return id;
	}
	protected void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	protected void setName(String name) {
		this.name = name;
	}
	protected void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	protected void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
    /**
     * Checks if two location objects are equal
     * @param o The object to be compared 
     * @return True of the two objects are equal, false otherwise
     */
	public boolean equals(Object o) {
		if(o == null) return false;
		if(o == this) return true;
		if(o.getClass() != this.getClass()) return false;
		return ((Location)o).getId() == getId();
	}		
}
