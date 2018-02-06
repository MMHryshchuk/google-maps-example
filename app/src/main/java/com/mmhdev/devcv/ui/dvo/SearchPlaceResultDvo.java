package com.mmhdev.devcv.ui.dvo;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by on 13.12.16.
 */

public class SearchPlaceResultDvo {

    private String id;
    private String placeId;
    private String name;
    private LatLng location;

    public SearchPlaceResultDvo() {
    }

    public SearchPlaceResultDvo(String id, String placeId, String name,LatLng location) {
        this.id = id;
        this.placeId = placeId;
        this.name = name;
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LatLng getLocation() {
        return location;
    }

    public void setLocation(LatLng location) {
        this.location = location;
    }
}
