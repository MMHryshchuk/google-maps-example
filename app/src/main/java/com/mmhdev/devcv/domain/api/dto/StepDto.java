package com.mmhdev.devcv.domain.api.dto;

import java.util.List;

/**
 * Created by on 07.12.16.
 */

public class StepDto {

    DistanceDto distance;
    DurationDto duration;
    EndLocationDto end_location;
    PolylineDto polyline;
    StartLocationDto start_location;
    List<StepStepDto> steps;
    TransitDetailsDto transit_details;
    String travel_mode;

    public StepDto() {
    }

    public TransitDetailsDto getTransit_details() {
        return transit_details;
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

    public List<StepStepDto> getSteps() {
        return steps;
    }

    public String getTravel_mode() {
        return travel_mode;
    }
}
