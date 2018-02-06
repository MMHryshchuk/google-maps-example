package com.mmhdev.devcv.ui.dvo;

import java.util.List;

/**
 * Created by on 14.12.16.
 */

public class PlaceDetailDvo {

    private String name;
    private List<BusDvo> listBus;

    public PlaceDetailDvo() {
    }

    public PlaceDetailDvo(String name, List<BusDvo> listBus) {
        this.name = name;
        this.listBus = listBus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BusDvo> getListBus() {
        return listBus;
    }

    public void setListBus(List<BusDvo> listBus) {
        this.listBus = listBus;
    }
}
