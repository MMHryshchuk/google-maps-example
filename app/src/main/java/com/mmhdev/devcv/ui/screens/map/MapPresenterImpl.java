package com.mmhdev.devcv.ui.screens.map;

import android.location.Location;
import android.util.Log;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.mmhdev.devcv.core.android.App;
import com.mmhdev.devcv.core.mvp.BasePresenter;
import com.mmhdev.devcv.core.rx.SimpleSubscriber;
import com.mmhdev.devcv.core.utils.StringUtils;
import com.mmhdev.devcv.domain.use_case.MapUseCase;
import com.mmhdev.devcv.domain.use_case.UserUseCase;
import com.mmhdev.devcv.ui.dvo.BusDvo;
import com.mmhdev.devcv.ui.dvo.DriversDvo;
import com.mmhdev.devcv.ui.dvo.MapDataDvo;
import com.mmhdev.devcv.ui.dvo.PlaceDetailDvo;
import com.mmhdev.devcv.ui.dvo.SearchPlaceResultDvo;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;

/**
 */
public class MapPresenterImpl extends BasePresenter<MapView> implements MapPresenter{

    private UserUseCase userUseCase;
    private MapUseCase mapUseCase;
    private final int type;
    private boolean isCameraDisable;
    private LatLng lastKnownLocation;
    int i = 0;


    @Inject
    public MapPresenterImpl(int type,
                            App app,
                            UserUseCase userUseCase,
                            MapUseCase mapUseCase) {
        this.userUseCase = userUseCase;
        this.type = type;
        this.isCameraDisable = false;
        this.mapUseCase = mapUseCase;
    }

    @Override
    protected void onViewAttached() {
        getView().showProgress();
    }

    @Override
    public void updateData(double lat, double lon) {
        addSubscription(userUseCase.getMapData(type, lat, lon).subscribe(new SimpleSubscriber<MapDataDvo>(){
            @Override
            public void onNext(MapDataDvo item) {
                if (getView() == null) return;

                getView().hideProgress();
                getView().removeOldMapData();
                getView().showMyPosition(lat, lon);
                getView().showMapData(item);
                if (isCameraDisable || lastKnownLocation == null){
                    getView().moveCamera(lat, lon);
                }

                lastKnownLocation = new LatLng(lat, lon);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                getView().showMyPosition(lastKnownLocation.latitude, lastKnownLocation.longitude);
            }
        }));
    }

    @Override
    public void onDisableCameraMovement(boolean disable) {
        isCameraDisable = disable;
        if (isCameraDisable){
            getView().cameraMovementDisable();
            if (lastKnownLocation != null)
                getView().moveCamera(lastKnownLocation.latitude, lastKnownLocation.longitude);
        } else {
            getView().cameraMovementEnable();
        }
    }

    @Override
    public void onMarkerClicked(Marker markerOptions) {
        i++;
    }

    @Override
    public void onFabClick() {
        if(getView() == null) return;
        getView().openDirection();
    }

    @Override
    public void searchPlace(LatLng location) {
        if (getView() == null) return;
        addSubscription(mapUseCase.getPlaceResult(location).subscribe(new SimpleSubscriber<SearchPlaceResultDvo>(){
            @Override
            public void onNext(SearchPlaceResultDvo item) {
                super.onNext(item);
                if (!StringUtils.isNullEmpty(item.getId())){
                    getPlaceInfo(item.getPlaceId());
                    getView().showBusMarker(new LatLng(
                            item.getLocation().latitude,
                            item.getLocation().longitude)
                    );
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        }));
    }

    @Override
    public void getPlaceInfo(String placeId) {
        if (getView() == null) return;
        addSubscription(mapUseCase.getPlaceInfo(placeId).subscribe(new SimpleSubscriber<PlaceDetailDvo>(){
            @Override
            public void onNext(PlaceDetailDvo item) {
                super.onNext(item);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        }));
    }

    @Override
    public void getBusRouts(String busNum) {
        if (getView() == null) return;
        addSubscription(mapUseCase.getDrivers(busNum).observeOn(AndroidSchedulers.mainThread()).subscribe(new SimpleSubscriber<List<DriversDvo>>(){
            @Override
            public void onNext(List<DriversDvo> item) {
                super.onNext(item);
                getView().showBusDrivers(item,busNum);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        }));
    }

    @Override
    public void onLocationChange(Location location) {
        if (location == null)
            return;
        updateData(location.getLatitude(), location.getLongitude());
    }

    @Override
    public void onShowSetupLocationSettingsPopup() {

    }

    @Override
    public void onResolutionRequired(Status status) {

    }
}
