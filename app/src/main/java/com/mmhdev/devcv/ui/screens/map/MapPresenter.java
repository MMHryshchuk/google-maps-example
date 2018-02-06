package com.mmhdev.devcv.ui.screens.map;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.mmhdev.devcv.core.mvp.Presenter;
import com.mmhdev.devcv.location.FusedLocationProvider;

/**
 */
public interface MapPresenter extends Presenter<MapView>, FusedLocationProvider.OnLocationChangeListener{
    void updateData(double lat, double lon);
    void onDisableCameraMovement(boolean disable);
    void onMarkerClicked(Marker markerOptions);
    void onFabClick();
    void searchPlace(LatLng location);
    void getPlaceInfo(String placeId);
    void getBusRouts(String busNum);
}
