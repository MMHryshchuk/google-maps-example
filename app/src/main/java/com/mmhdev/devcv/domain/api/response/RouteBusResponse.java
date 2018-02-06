package com.mmhdev.devcv.domain.api.response;

import com.mmhdev.devcv.domain.api.dto.RouteBusDto;

import java.util.List;

/**
 * Created by on 16.12.16.
 */

public class RouteBusResponse {

    List<RouteBusDto> routes;

    public RouteBusResponse() {
    }

    public List<RouteBusDto> getRoutes() {
        return routes;
    }
}
