package com.mmhdev.devcv.ui.dvo;

import com.mmhdev.devcv.domain.api.DirectionsApi;

import java.util.List;

/**
 */
public class MapDataDvo {
    List<EntityDvo> mapData;

    public MapDataDvo(List<EntityDvo> mapData) {
        this.mapData = mapData;
    }

    public List<EntityDvo> getEntities() {
        return mapData;
    }
}
