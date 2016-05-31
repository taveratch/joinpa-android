package io.joinpa.joinpa.models;

import java.io.Serializable;

/**
 * Created by TAWEESOFT on 5/29/16 AD.
 */
public class Place implements Serializable {
    private String name;
    private double lat,lon;

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
}
