package com.mmhdev.devcv.ui.dvo;

import java.util.List;

/**
 * Created by on 13.12.16.
 */

public class PlaceListDvo {

    private List<SearchPlaceResultDvo> plaeList;

    public PlaceListDvo() {
    }

    public PlaceListDvo(List<SearchPlaceResultDvo> plaeList) {
        this.plaeList = plaeList;
    }

    public List<SearchPlaceResultDvo> getPlaeList() {
        return plaeList;
    }

    public void setPlaeList(List<SearchPlaceResultDvo> plaeList) {
        this.plaeList = plaeList;
    }
}
