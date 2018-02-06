package com.mmhdev.devcv.ui.dvo;

/**
 * Created by on 05.12.16.
 */

public class PlaceAutocomplete {

    public CharSequence placeId;
    public CharSequence description;

    public PlaceAutocomplete(CharSequence placeId, CharSequence description) {
        this.placeId = placeId;
        this.description = description;
    }

    @Override
    public String toString() {
        return description.toString();
    }
}
