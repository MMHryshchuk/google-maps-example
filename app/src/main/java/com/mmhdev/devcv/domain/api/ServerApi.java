package com.mmhdev.devcv.domain.api;

import com.mmhdev.devcv.domain.api.dto.RouteBusDto;
import com.mmhdev.devcv.ui.dvo.BusDvo;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by on 18.09.17.
 */

public interface ServerApi {

    @GET("bus/getbyname")
    Observable<List<RouteBusDto>> getBusPos(@Query(value = "name") String name);
}
