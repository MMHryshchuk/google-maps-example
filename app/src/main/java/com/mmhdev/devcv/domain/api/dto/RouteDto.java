package com.mmhdev.devcv.domain.api.dto;

import java.util.List;

/**
 * Created by on 07.12.16.
 */

public class RouteDto {
    FareDto fare;
    List<LegsDto> legs;
    OverviewPolylineDto overview_polyline;

    public RouteDto() {
    }

    public FareDto getFare() {
        return fare;
    }

    public List<LegsDto> getLegs() {
        return legs;
    }

    public OverviewPolylineDto getOverview_polyline() {
        return overview_polyline;
    }
}
