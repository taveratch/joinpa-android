package io.joinpa.joinpa.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Place implements Serializable {

    @SerializedName("name")
    private String name;

    @SerializedName("lat")
    private double lat;

    @SerializedName("lon")
    private double lon;

    @SerializedName("isUseMap")
    private boolean isUseMap;

    public Place(String name, double lat, double lon) {
        this.name = name;
        this.lat = lat;
        this.lon = lon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public boolean isUseMap() {
        return isUseMap;
    }

    public void setUseMap(boolean useMap) {
        isUseMap = useMap;
    }
}
