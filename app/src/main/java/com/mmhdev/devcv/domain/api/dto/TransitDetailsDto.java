package com.mmhdev.devcv.domain.api.dto;

/**
 * Created by on 07.12.16.
 */

public class TransitDetailsDto {

    ArrivalStopDto departure_stop;
    ArrivalStopDto arrival_stop;
    DepartureTimeDto departure_time;
    String headsign;
    long headway;
    LineDto line;
    long num_stop;

    public TransitDetailsDto() {
    }

    public ArrivalStopDto getDeparture_stop() {
        return departure_stop;
    }

    public ArrivalStopDto getArrival_stop() {
        return arrival_stop;
    }

    public String getHeadsign() {
        return headsign;
    }

    public long getHeadway() {
        return headway;
    }

    public LineDto getLine() {
        return line;
    }

    public long getNum_stop() {
        return num_stop;
    }

    public DepartureTimeDto getDeparture_time() {
        return departure_time;
    }
}
