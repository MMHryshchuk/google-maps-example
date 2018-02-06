package com.mmhdev.devcv.domain.api.dto;

/**
 * Created by on 13.12.16.
 */

public class SearchPlaceResultDto {

    GeometryDto geometry;
    String id;
    String name;
    String place_id;
    String url;



    public SearchPlaceResultDto() {
    }

    public GeometryDto getGeometry() {
        return geometry;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPlace_id() {
        return place_id;
    }

    public String getUrl() {
        return url;
    }
}
