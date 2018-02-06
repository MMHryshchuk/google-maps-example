package com.mmhdev.devcv.ui.screens.map;


import com.google.android.gms.maps.model.LatLng;
import com.mmhdev.devcv.ui.dvo.BusDvo;
import com.mmhdev.devcv.ui.dvo.DriversDvo;
import com.mmhdev.devcv.ui.dvo.MapDataDvo;
import com.mmhdev.devcv.ui.dvo.PlaceDetailDvo;
import com.mmhdev.devcv.ui.dvo.StepsDvo;

import java.util.List;

/**
 */
public interface MapView {
    void showProgress();
    void hideProgress();
    void showMyPosition(double lat, double lan);
    void showMapData(MapDataDvo mapDataDvo);
    void removeOldMapData();

    void openDirection();

    void showBusView();
    void hideBusView();

    void cameraMovementDisable();
    void cameraMovementEnable();
    void moveCamera(double lat, double lon);

    void showPolyline(List<StepsDvo> steps);
    void showBusMarker(LatLng location);

    void showBusDrivers(List<DriversDvo> drivers, String busNum);
    void deleteBusss();
}
