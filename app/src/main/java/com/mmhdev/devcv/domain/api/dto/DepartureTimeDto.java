package com.mmhdev.devcv.domain.api.dto;

/**
 * Created by on 16.12.16.
 */

public class DepartureTimeDto {

    String text;
    String time_zone;
    long value;

    public DepartureTimeDto() {
    }

    public String getText() {
        return text;
    }

    public String getTime_zone() {
        return time_zone;
    }

    public long getValue() {
        return value;
    }
}
