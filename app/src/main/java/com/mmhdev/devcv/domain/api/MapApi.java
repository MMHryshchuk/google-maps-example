package com.mmhdev.devcv.domain.api;

import com.mmhdev.devcv.domain.api.dto.RouteBusDto;
import com.mmhdev.devcv.domain.api.response.PlaceDetailsResponse;
import com.mmhdev.devcv.domain.api.response.PlaceResponse;
import com.mmhdev.devcv.domain.api.response.RouteBusResponse;
import com.mmhdev.devcv.ui.dvo.PlaceDetailDvo;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by on 13.12.16.
 */

public interface MapApi {

    @GET("place/nearbysearch/json")
    Observable<PlaceResponse> getPlaceNearby(@Query(value = "location",encoded = false) String location,
                                             @Query(value = "radius") String radius,
                                             @Query(value = "types") String types,
                                             @Query("language") String language,
                                             @Query("key") String key);

    @GET("place/details/json")
    Observable<PlaceDetailsResponse> getPlaceInfo(@Query(value = "placeid") String placeid,
                                                  @Query("language") String language,
                                                  @Query("key") String key);


}
