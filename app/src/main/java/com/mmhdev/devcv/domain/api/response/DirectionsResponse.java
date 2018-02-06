package com.mmhdev.devcv.domain.api.response;

import com.mmhdev.devcv.domain.api.dto.GeocodedWaypointDto;
import com.mmhdev.devcv.domain.api.dto.RouteDto;

import java.util.List;

/**
 * Created by on 07.12.16.
 */

public class DirectionsResponse {

    List<GeocodedWaypointDto> geocoded_waypoints;
    List<RouteDto> routes;
    String status;

    public DirectionsResponse() {
    }

    public List<GeocodedWaypointDto> getGeocoded_waypoints() {
        return geocoded_waypoints;
    }

    public List<RouteDto> getRoutes() {
        return routes;
    }

    public String getStatus() {
        return status;
    }
}
