package com.mmhdev.devcv.ui.dvo;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by on 16.12.16.
 */

public class DriversDvo {

    private String numBus;
    private long idNumBus;
    private LatLng location;

    public DriversDvo() {
    }

    public DriversDvo(String numBus, long idNumBus, LatLng location) {
        this.numBus = numBus;
        this.idNumBus = idNumBus;
        this.location = location;
    }

    public String getNumBus() {
        return numBus;
    }

    public void setNumBus(String numBus) {
        this.numBus = numBus;
    }

    public long getIdNumBus() {
        return idNumBus;
    }

    public void setIdNumBus(long idNumBus) {
        this.idNumBus = idNumBus;
    }

    public LatLng getLocation() {
        return location;
    }

    public void setLocation(LatLng location) {
        this.location = location;
    }
}

