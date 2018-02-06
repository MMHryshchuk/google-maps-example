package com.mmhdev.devcv.domain.api.dto;

/**
 * Created by on 07.12.16.
 */

public class ArrivalStopDto {

    EndLocationDto location;
    String  name;

    public ArrivalStopDto() {
    }

    public EndLocationDto getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }


}
