package com.github.pozo.model.route;

import com.github.pozo.model.LineId;
import com.github.pozo.model.RouteId;

import static com.google.common.base.Preconditions.checkNotNull;

public class RouteBuilder {
    private LineId lineId;
    private RouteId routeId;
    private String name;

    RouteBuilder() {

    }

    public RouteBuilder setLineId(LineId lineId) {
        this.lineId = lineId;
        return this;
    }

    public RouteBuilder setRouteId(RouteId routeId) {
        this.routeId = routeId;
        return this;
    }

    public RouteBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public Route createRoute() {
        checkNotNull(lineId, "lineId cant be null!");
        checkNotNull(routeId, "routeId cant be null!");
        checkNotNull(name, "name cant be null!");

        return new Route(lineId, routeId, name);
    }
}