package com.mmhdev.devcv.domain.api.dto;

/**
 * Created by on 07.12.16.
 */

public class FareDto {

    String currency;
    String text;
    String value;

    public FareDto() {
    }

    public String getCurrency() {
        return currency;
    }

    public String getText() {
        return text;
    }

    public String getValue() {
        return value;
    }
}
