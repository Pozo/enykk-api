package com.github.pozo.model.arrival;

import com.github.pozo.model.LineId;
import com.github.pozo.model.RouteId;
import com.github.pozo.model.VehicleType;

public class ArrivalBuilder {
    private LineId lineId;
    private String lineName;
    private RouteId routeId;
    private double expectedArrival;
    private boolean arrived;
    private VehicleType vehicleType;

    ArrivalBuilder() {
        
    }

    public ArrivalBuilder setLineId(LineId lineId) {
        this.lineId = lineId;
        return this;
    }

    public ArrivalBuilder setLineName(String lineName) {
        this.lineName = lineName;
        return this;
    }

    public ArrivalBuilder setRouteId(RouteId routeId) {
        this.routeId = routeId;
        return this;
    }

    public ArrivalBuilder setExpectedArrival(double expectedArrival) {
        this.expectedArrival = expectedArrival;
        return this;
    }

    public ArrivalBuilder setArrived(boolean arrived) {
        this.arrived = arrived;
        return this;
    }

    public ArrivalBuilder setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
        return this;
    }

    public Arrival createArrival() {
        return new Arrival(lineId, lineName, routeId, expectedArrival, arrived, vehicleType);
    }
}