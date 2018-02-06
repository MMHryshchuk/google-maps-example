package com.mmhdev.devcv.domain.api.dto;

/**
 * Created by on 07.12.16.
 */

public class ArrivalTimeDto {

    String text;
    String time_zone;
    long value;

    public ArrivalTimeDto() {
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
