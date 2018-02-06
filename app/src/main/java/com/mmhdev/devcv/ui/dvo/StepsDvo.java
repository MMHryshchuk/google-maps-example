package com.mmhdev.devcv.ui.dvo;

/**
 * Created by on 08.12.16.
 */

public class StepsDvo {

    private String travelMode;
    private String polyline;


    public StepsDvo() {
    }

    public StepsDvo(String travelMode, String polyline) {
        this.travelMode = travelMode;
        this.polyline = polyline;
    }

    public String getTravelMode() {
        return travelMode;
    }

    public void setTravelMode(String travelMode) {
        this.travelMode = travelMode;
    }

    public String getPolyline() {
        return polyline;
    }

    public void setPolyline(String polyline) {
        this.polyline = polyline;
    }
}
