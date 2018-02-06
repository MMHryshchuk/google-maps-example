package com.mmhdev.devcv.domain.api.dto;

import java.util.List;

/**
 * Created by on 07.12.16.
 */

public class LegsDto {

    ArrivalTimeDto arrival_time;
    DistanceDto distance;
    DurationDto duration;
    String end_address;
    EndLocationDto end_location;
    String start_address;
    StartLocationDto start_location;
    List<StepDto> steps;

    public LegsDto() {
    }

    public ArrivalTimeDto getArrival_time() {
        return arrival_time;
    }

    public DistanceDto getDistance() {
        return distance;
    }

    public DurationDto getDuration() {
        return duration;
    }

    public String getEnd_address() {
        return end_address;
    }

    public EndLocationDto getEnd_location() {
        return end_location;
    }

    public String getStart_address() {
        return start_address;
    }

    public StartLocationDto getStart_location() {
        return start_location;
    }

    public List<StepDto> getSteps() {
        return steps;
    }


}
