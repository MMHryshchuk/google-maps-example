package com.mmhdev.devcv.ui.dvo;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by on 07.12.16.
 */

public class DirectionRequestDvo {

    private LatLng startPoint;
    private LatLng endPoint;

    public DirectionRequestDvo() {
    }

    public DirectionRequestDvo(LatLng startPoint, LatLng endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    public LatLng getStartPoint() {
        return startPoint;
    }

    public LatLng getEndPoint() {
        return endPoint;
    }

    public void setStartPoint(LatLng startPoint) {
        this.startPoint = startPoint;
    }

    public void setEndPoint(LatLng endPoint) {
        this.endPoint = endPoint;
    }
}
