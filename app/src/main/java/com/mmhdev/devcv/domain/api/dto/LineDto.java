package com.mmhdev.devcv.domain.api.dto;

/**
 * Created by on 07.12.16.
 */

public class LineDto {

    String name;
    String short_name;
    int num_stops;

    public LineDto() {
    }

    public String getName() {
        return name;
    }

    public String getShort_name() {
        return short_name;
    }

    public int getNum_stops() {
        return num_stops;
    }
}
