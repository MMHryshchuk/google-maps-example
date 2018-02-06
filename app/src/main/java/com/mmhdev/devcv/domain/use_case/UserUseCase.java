package com.mmhdev.devcv.domain.use_case;


import com.mmhdev.devcv.ui.dvo.MapDataDvo;

import rx.Observable;

/**
 */
public interface UserUseCase {
    Observable<MapDataDvo> getMapData(int mode, double lat, double lon);
    Observable<Boolean> generateEntities(double lat, double lon);
}
