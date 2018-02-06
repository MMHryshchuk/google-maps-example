package com.mmhdev.devcv.domain.use_case;

import com.google.android.gms.maps.model.LatLng;
import com.mmhdev.devcv.ui.dvo.DirectionsDvo;
import com.mmhdev.devcv.ui.dvo.RoadsDvo;

import rx.Observable;

/**
 * Created by on 07.12.16.
 */

public interface DirectionsUseCase {

    Observable<DirectionsDvo> getRoads(LatLng start, LatLng end);
}
