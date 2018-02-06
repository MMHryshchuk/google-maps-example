package com.mmhdev.devcv.domain.use_case;

import com.google.android.gms.maps.model.LatLng;
import com.mmhdev.devcv.ui.dvo.DriversDvo;
import com.mmhdev.devcv.ui.dvo.PlaceDetailDvo;
import com.mmhdev.devcv.ui.dvo.SearchPlaceResultDvo;

import java.util.List;

import rx.Observable;

/**
 * Created by on 13.12.16.
 */

public interface MapUseCase {

    Observable<SearchPlaceResultDvo> getPlaceResult(LatLng location);

    Observable<PlaceDetailDvo> getPlaceInfo(String placeId);

    Observable<List<DriversDvo>> getDrivers(String numBus);

}
