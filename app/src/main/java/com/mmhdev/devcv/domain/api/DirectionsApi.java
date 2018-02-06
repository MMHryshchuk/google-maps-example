package com.mmhdev.devcv.domain.api;

import com.mmhdev.devcv.domain.api.response.DirectionsResponse;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by on 07.12.16.
 */

public interface DirectionsApi {

    @GET("directions/json")
    Observable<DirectionsResponse> getDirectionsRoads(@Query(value = "origin",encoded = false) String position,
                                                      @Query(value = "destination", encoded = false) String destination,
                                                      @Query(value = "mode") String mode,
                                                      @Query(value = "alternatives") boolean alternative,
                                                      @Query("language") String language,
                                                      @Query("key") String key);


}
