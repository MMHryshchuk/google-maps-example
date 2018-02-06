package com.mmhdev.devcv.domain.api.dto;

/**
 * Created by on 07.12.16.
 */

public class StepStepDto {

    DistanceDto distance;
    DurationDto duration;
    EndLocationDto end_location;
    PolylineDto polyline;
    StartLocationDto start_location;
    String travel_mode;

    public StepStepDto() {
    }

    public DistanceDto getDistance() {
        return distance;
    }

    public DurationDto getDuration() {
        return duration;
    }

    public EndLocationDto getEnd_location() {
        return end_location;
    }

    public PolylineDto getPolyline() {
        return polyline;
    }

    public StartLocationDto getStart_location() {
        return start_location;
    }

    public String getTravel_mode() {
        return travel_mode;
    }
}
