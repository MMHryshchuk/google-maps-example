package com.mmhdev.devcv.domain.use_case.impl;

import com.google.android.gms.maps.model.LatLng;
import com.mmhdev.devcv.core.android.AuthRetrofit;
import com.mmhdev.devcv.core.executors.PostExecutionThread;
import com.mmhdev.devcv.core.executors.ThreadExecutor;
import com.mmhdev.devcv.domain.api.MapApi;
import com.mmhdev.devcv.domain.api.ServerApi;
import com.mmhdev.devcv.domain.api.dto.RouteBusDto;
import com.mmhdev.devcv.domain.api.dto.SearchPlaceResultDto;
import com.mmhdev.devcv.domain.use_case.MapUseCase;
import com.mmhdev.devcv.ui.dvo.BusDvo;
import com.mmhdev.devcv.ui.dvo.DriversDvo;
import com.mmhdev.devcv.ui.dvo.PlaceDetailDvo;
import com.mmhdev.devcv.ui.dvo.SearchPlaceResultDvo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Retrofit;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by on 13.12.16.
 */

public class MapUseCaseImpl implements MapUseCase {

    private ThreadExecutor threadExecutor;
    private PostExecutionThread postExecutionThread;
    private MapApi directionsApi;
    private ServerApi serverApi;


    public MapUseCaseImpl(ThreadExecutor threadExecutor,
                          PostExecutionThread postExecutionThread,
                          Retrofit retrofit,
                          AuthRetrofit authRetrofit ) {
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
        this.directionsApi = authRetrofit.create(MapApi.class);
        this.serverApi = retrofit.create(ServerApi.class);
    }


    @Override
    public Observable<SearchPlaceResultDvo> getPlaceResult(LatLng location) {
        return directionsApi.getPlaceNearby(location.latitude+","+location.longitude,
                "5",
                "bus_station",
                "uk",
                "AIzaSyABZB5oNTj85sV82BGibagcat_wQzECVyg")
                .map(placeResponse -> {
                    if (placeResponse.getResult() != null){
                        SearchPlaceResultDto result = placeResponse.getResult().get(0);
                        return new SearchPlaceResultDvo(
                                result.getId(),
                                result.getPlace_id(),
                                result.getName(),
                                new LatLng(result.getGeometry().getLocation().getLat(),
                                        result.getGeometry().getLocation().getLng())
                                );
                    }
                    return new SearchPlaceResultDvo();
                })
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler());
    }

    @Override
    public Observable<PlaceDetailDvo> getPlaceInfo(String placeId) {
        return directionsApi.getPlaceInfo(placeId, "uk","AIzaSyABZB5oNTj85sV82BGibagcat_wQzECVyg")
                .map(placeDetailsResponse -> {

                    List<BusDvo> buslist = new ArrayList<BusDvo>();

                    buslist.add(new BusDvo("32"));
                    buslist.add(new BusDvo("39"));
                    return new PlaceDetailDvo(placeDetailsResponse.getResult().getName(),buslist);
                })
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler());
    }

    @Override
    public Observable<List<DriversDvo>> getDrivers(String numBus) {
        return serverApi.getBusPos(numBus)
                .map(routeBusResponse -> {
                    List<DriversDvo> list = new ArrayList<>();
                    for (RouteBusDto bus : routeBusResponse) {
                        list.add(new DriversDvo(
                                bus.getName(),
                                bus.getId(),
                                new LatLng(bus.getLatitude(), bus.getLongitude())
                        ));
                    }
                    return list;
                })
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler());

    }
}
