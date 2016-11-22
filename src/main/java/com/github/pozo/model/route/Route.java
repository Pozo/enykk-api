package com.github.pozo.model.route;

import com.github.pozo.model.LineId;
import com.github.pozo.model.RouteId;

public class Route {
    final LineId lineId;
    final RouteId routeId;
    final String name;

    Route(LineId lineId, RouteId routeId, String name) {
        this.lineId = lineId;
        this.routeId = routeId;
        this.name = name;
    }

    public static RouteBuilder builder() {
        return new RouteBuilder();
    }

    public LineId getLineId() {
        return lineId;
    }

    public RouteId getRouteId() {
        return routeId;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Route{" +
                "lineId=" + lineId +
                ", routeId=" + routeId +
                ", name='" + name + '\'' +
                '}';
    }
}
