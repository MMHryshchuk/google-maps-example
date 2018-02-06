package com.mmhdev.devcv.domain.api.dto;

import java.util.List;

/**
 * Created by on 07.12.16.
 */

public class GeocodedWaypointDto {

    String geocoder_status;
    String place_id;
    List<String> types;

    public GeocodedWaypointDto() {
    }

    public String getGeocoder_status() {
        return geocoder_status;
    }

    public String getPlace_id() {
        return place_id;
    }

    public List<String> getTypes() {
        return types;
    }
}
