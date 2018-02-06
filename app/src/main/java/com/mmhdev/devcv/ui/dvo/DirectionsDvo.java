package com.mmhdev.devcv.ui.dvo;

import java.util.List;

/**
 * Created by on 08.12.16.
 */

public class DirectionsDvo {

    private List<RouteDvo> routes;

    public DirectionsDvo() {
    }

    public DirectionsDvo(List<RouteDvo> routes) {
        this.routes = routes;
    }

    public List<RouteDvo> getRoutes() {
        return routes;
    }

    public void setRoutes(List<RouteDvo> routes) {
        this.routes = routes;
    }
}
