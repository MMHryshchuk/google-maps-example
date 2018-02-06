package com.mmhdev.devcv.domain.api.dto;

/**
 * Created by on 16.12.16.
 */

public class RouteBusDto {

    long Id;
    String Name;
    double Longitude;
    double Latitude;

    public RouteBusDto() {
    }

    public long getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public double getLongitude() {
        return Longitude;
    }

    public double getLatitude() {
        return Latitude;
    }
}
