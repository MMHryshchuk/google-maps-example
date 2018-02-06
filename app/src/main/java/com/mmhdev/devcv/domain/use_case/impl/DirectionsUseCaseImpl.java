package com.mmhdev.devcv.domain.use_case.impl;

import com.google.android.gms.maps.model.LatLng;
import com.mmhdev.devcv.core.android.AuthRetrofit;
import com.mmhdev.devcv.core.executors.PostExecutionThread;
import com.mmhdev.devcv.core.executors.ThreadExecutor;
import com.mmhdev.devcv.core.utils.StringUtils;
import com.mmhdev.devcv.domain.api.DirectionsApi;
import com.mmhdev.devcv.domain.api.dto.RouteDto;
import com.mmhdev.devcv.domain.api.dto.StepDto;
import com.mmhdev.devcv.domain.use_case.DirectionsUseCase;
import com.mmhdev.devcv.ui.dvo.DirectionsDvo;
import com.mmhdev.devcv.ui.dvo.RoadsDvo;
import com.mmhdev.devcv.ui.dvo.RouteDvo;
import com.mmhdev.devcv.ui.dvo.StepsDvo;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by on 07.12.16.
 */

public class DirectionsUseCaseImpl implements DirectionsUseCase {

    private ThreadExecutor threadExecutor;
    private PostExecutionThread postExecutionThread;
    private DirectionsApi directionsApi;

    public DirectionsUseCaseImpl(
            ThreadExecutor threadExecutor,
            PostExecutionThread postExecutionThread,
            AuthRetrofit authRetrofit
    ) {
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
        this.directionsApi = authRetrofit.create(DirectionsApi.class);
    }

    @Override
    public Observable<DirectionsDvo> getRoads(LatLng start, LatLng end) {
        return directionsApi.getDirectionsRoads(start.latitude + "," + start.longitude,
                end.latitude + "," + end.longitude,
                "transit",
                true,
                "uk",
                "AIzaSyABZB5oNTj85sV82BGibagcat_wQzECVyg")
                .map(directionsResponse -> {
                    List<RouteDvo> routs = new ArrayList<>(directionsResponse.getRoutes().size());
                    for (RouteDto routeDto : directionsResponse.getRoutes()){
                        String numBus = "";
                        String from = "";
                        String time = "";
                        LatLng location = null;
                        List<StepsDvo> stepsDvos = new ArrayList<>();
                        for (StepDto stepDto : routeDto.getLegs().get(0).getSteps()){
                            stepsDvos.add(new StepsDvo(stepDto.getTravel_mode(),stepDto.getPolyline().getPoints()));
                            if (stepDto.getTravel_mode() != null && stepDto.getTravel_mode().equals("TRANSIT")){
                                numBus = stepDto.getTransit_details().getLine().getShort_name();
                                from = stepDto.getTransit_details().getDeparture_stop().getName();
                                time = stepDto.getTransit_details().getDeparture_time().getText();
                                location = new LatLng(
                                        stepDto.getTransit_details().getDeparture_stop().getLocation().getLat(),
                                        stepDto.getTransit_details().getDeparture_stop().getLocation().getLat());
                            }
                        }
                        routs.add(new RouteDvo(routeDto.getLegs().get(0).getDuration().getText(),
                                routeDto.getLegs().get(0).getDistance().getText(),
                                routeDto.getOverview_polyline().getPoints(),
                                routeDto.getFare() == null ? "" : routeDto.getFare().getText(),
                                numBus,
                                StringUtils.isNullEmpty(from) ? "" : "від " + from,
                                StringUtils.isNullEmpty(time) ? "" : "час прибуття " + time ,
                                location,
                                stepsDvos)
                        );

                    }
                    return new DirectionsDvo(routs);
                })
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler());
    }

}
