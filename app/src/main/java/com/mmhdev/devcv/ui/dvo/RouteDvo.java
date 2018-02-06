package com.mmhdev.devcv.ui.dvo;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.model.EncodedPolyline;

import java.util.List;

/**
 * Created by on 08.12.16.
 */

public class RouteDvo {

    private String durations;
    private String distance;
    private String overviewPolyline;
    private String fare;
    private String numBus;
    private String from;
    private String time;
    private LatLng location;
    private List<StepsDvo> steps;

    public RouteDvo() {
    }

    public RouteDvo(String durations, String distance, String overviewPolyline, String fare, String numBus,String from, String time,LatLng location, List<StepsDvo> steps) {
        this.durations = durations;
        this.distance = distance;
        this.overviewPolyline = overviewPolyline;
        this.fare = fare;
        this.numBus = numBus;
        this.from = from;
        this.time = time;
        this.location = location;
        this.steps = steps;
    }

    public String getDurations() {
        return durations;
    }

    public void setDurations(String durations) {
        this.durations = durations;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getOverviewPolyline() {
        return overviewPolyline;
    }

    public void setOverviewPolyline(String overviewPolyline) {
        this.overviewPolyline = overviewPolyline;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getFare() {
        return fare;
    }

    public void setFare(String fare) {
        this.fare = fare;
    }

    public String getNumBus() {
        return numBus;
    }

    public void setNumBus(String numBus) {
        this.numBus = numBus;
    }

    public List<StepsDvo> getSteps() {
        return steps;
    }

    public void setSteps(List<StepsDvo> steps) {
        this.steps = steps;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public LatLng getLocation() {
        return location;
    }

    public void setLocation(LatLng location) {
        this.location = location;
    }
}
