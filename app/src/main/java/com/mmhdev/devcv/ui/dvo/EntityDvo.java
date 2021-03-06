package com.mmhdev.devcv.ui.dvo;


import com.mmhdev.devcv.domain.entities.MainEntity;

/**
 */
public class EntityDvo {
    MainEntity mainEntity;

    public EntityDvo(MainEntity mainEntity) {
        this.mainEntity = mainEntity;
    }

    public long getId() {
        return mainEntity.getId();
    }

    public String getName() {
        return mainEntity.getName();
    }

    public int getPhoto() {
        return mainEntity.getIcon();
    }

    public int getRank() {
        return mainEntity.getPower();
    }

    public double getLat() {
        return mainEntity.getLatitude();
    }

    public double getLan() {
        return mainEntity.getLongitude();
    }
}
